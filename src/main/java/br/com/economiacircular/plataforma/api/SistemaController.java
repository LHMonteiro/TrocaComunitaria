package br.com.economiacircular.plataforma.api;
 
import br.com.economiacircular.plataforma.api.dto.LoginRequest;
import br.com.economiacircular.plataforma.api.dto.PublicacaoRequest;
import br.com.economiacircular.plataforma.api.dto.SolicitacaoRequest;
import br.com.economiacircular.plataforma.domain.*;
import br.com.economiacircular.plataforma.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;

@RestController
public class SistemaController {

    private final UsuarioRepository usuarios;  
    private final PublicacaoRepository publicacoes;  
    private final SolicitacaoRepository solicitacoes;    
    private final NotificacaoRepository notificacoes;   
    private final TransacaoRepository transacoes;     

   public SistemaController(UsuarioRepository usuarios,
                         PublicacaoRepository publicacoes,
                         SolicitacaoRepository solicitacoes,
                         NotificacaoRepository notificacoes,
                         TransacaoRepository transacoes) {
    this.usuarios = usuarios;
    this.publicacoes = publicacoes;
    this.solicitacoes = solicitacoes;
    this.notificacoes = notificacoes;
    this.transacoes = transacoes;
}

    @PostMapping("/api/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {

        usuario.inicializarSaldo();

        return usuarios.save(usuario);
    }

    @PostMapping("/api/usuarios/login")
    public Usuario loginUsuario(@RequestBody LoginRequest request) {

       Usuario usuario = buscarUsuarioPorEmail(request.email);    

        if (!usuario.senhaCorreta(request.senha)) {

            throw new IllegalArgumentException("Senha incorreta");
        }

         return usuario;
    }

    @GetMapping("/api/usuarios/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {

        return usuarios.findById(id)

                .orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));
    }

    @PostMapping("/api/publicacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public Publicacao criacaoDePublicacao(@RequestBody PublicacaoRequest request) {
        
        Publicacao publicacao = new Publicacao();
        
        publicacao.setTitulo(request.titulo);
        publicacao.setDescricao(request.descricao);
        publicacao.setTipo(request.tipo);
        publicacao.setValorCreditos(request.valorCreditos);
        publicacao.setDono(buscarUsuario(request.usuarioId));
        
            return publicacoes.save(publicacao);
    }

    @GetMapping("/api/publicacoes/feed/{usuarioId}")
    public List<Publicacao> feed(@PathVariable Long usuarioId) {
        
        buscarUsuario(usuarioId);

         return publicacoes.findByStatusAndDonoIdNot(StatusPublicacao.DISPONIVEL, usuarioId);
    }

    @GetMapping("/api/publicacoes/usuario/{usuarioId}")
    public List<Publicacao> publicacoesDoUsuario(@PathVariable Long usuarioId) {
        
         return publicacoes.findByDonoId(usuarioId);
    
    }

    @PatchMapping("/api/publicacoes/{id}/cancelar")
    public Publicacao cancelamentoDePublicacao(@PathVariable Long id, @RequestBody PublicacaoRequest request) {
        Publicacao publicacao = buscarPublicacao(id);
        
        publicacao.cancelar(request.usuarioId);
         
         return publicacoes.save(publicacao);       
    }
                                
    @PostMapping("/api/solicitacoes/publicacao/{publicacaoId}")         
    @ResponseStatus(HttpStatus.CREATED)    
    public Solicitacao pedidoPublicacao(@PathVariable Long publicacaoId,    
                                       @RequestBody SolicitacaoRequest request) {
        Publicacao publicacao = buscarPublicacao(publicacaoId);      
        
        Usuario interessado = buscarUsuario(request.usuarioId);     

        publicacao.validarSolicitante(interessado);

        publicacao.reservar();                                
            
        publicacoes.save(publicacao);    
           
                                    
        Solicitacao solicitacao = new Solicitacao();

        solicitacao.setPublicacao(publicacao);

        solicitacao.setInteressado(interessado);

        criarNotificacao(publicacao.getDono(),
                interessado.getNome() + " tem interesse em: " + publicacao.getTitulo());

        return solicitacoes.save(solicitacao);
    }      

    @GetMapping("/api/solicitacoes/recebidas/{usuarioId}")
    public List<Solicitacao> solicitacoesRecebidas(@PathVariable Long usuarioId) {
        return solicitacoes.findByPublicacaoDonoId(usuarioId);
    }

    @PostMapping("/api/solicitacoes/{id}/aceitar")
    @Transactional
    public Solicitacao aceitarSolicitacao(@PathVariable Long id, @RequestBody SolicitacaoRequest request) {
        
        Solicitacao solicitacao = buscarSolicitacao(id);
        Publicacao publicacao = solicitacao.getPublicacao();
        Usuario dono = publicacao.getDono();
        Usuario interessado = solicitacao.getInteressado();

        if (!dono.getId().equals(request.usuarioId)) {
            
            throw new IllegalArgumentException("Somente o dono pode aceitar esta solicitacao");
        
        }  
              
        if (solicitacao.getStatus() != StatusSolicitacao.PENDENTE) {    
                     
            throw new IllegalArgumentException("Esta solicitacao ja foi respondida");
        
        }   

        if (interessado.getSaldoCreditos() < publicacao.getValorCreditos()) {
                 
            throw new IllegalArgumentException("O interessado nao tem creditos suficientes");
        
        }    
                    
        // Não mexer nisso aqui. Complicado de mais.

        interessado.setSaldoCreditos(interessado.getSaldoCreditos() - publicacao.getValorCreditos());
        dono.setSaldoCreditos(dono.getSaldoCreditos() + publicacao.getValorCreditos());
        usuarios.save(interessado);
        usuarios.save(dono);

        publicacao.setStatus(StatusPublicacao.CONCLUIDA);
        publicacoes.save(publicacao);

        solicitacao.setStatus(StatusSolicitacao.ACEITA);
        solicitacoes.save(solicitacao);

        Transacao transacao = new Transacao();

        transacao.setPublicacao(publicacao);
        transacao.setPagador(interessado);
        transacao.setRecebedor(dono);
        transacao.setCreditos(publicacao.getValorCreditos());
        transacoes.save(transacao);

        criarNotificacao(dono, "Troca concluida: " + publicacao.getTitulo()
                + ". Contato: " + interessado.getContato());
        criarNotificacao(interessado, "Troca concluida: " + publicacao.getTitulo()
                + ". Contato: " + dono.getContato());

        return solicitacao;
    }

    @PostMapping("/api/solicitacoes/{id}/recusar")
    @Transactional
    public Solicitacao recusarSolicitacao(@PathVariable Long id, @RequestBody SolicitacaoRequest request) {
       
        Solicitacao solicitacao = buscarSolicitacao(id);      


        solicitacao.recusar(request.usuarioId);      
 
        publicacoes.save(solicitacao.getPublicacao());     

        criarNotificacao(solicitacao.getInteressado(), solicitacao.mensagemRecusa());  
 
        return solicitacoes.save(solicitacao);  
    }

    @GetMapping("/api/notificacoes/usuario/{usuarioId}")       
    public List<Notificacao> notificacoesDoUsuario(@PathVariable Long usuarioId) {

        return notificacoes.findByUsuarioIdOrderByCriadaEmDesc(usuarioId);       

    }

    @GetMapping("/api/transacoes/usuario/{usuarioId}")          
    public List<Transacao> transacoesDoUsuario(@PathVariable Long usuarioId) { 

         return transacoes.findByPagadorIdOrRecebedorIdOrderByCriadaEmDesc(usuarioId, usuarioId);   

    }      
        
    private Usuario buscarUsuarioPorEmail(String email) {

        return usuarios.findByEmail(email)      
                .orElseThrow(() -> new EntityNotFoundException("Email nao encontrado"));    

    }

    private Publicacao buscarPublicacao(Long id) {    

        return publicacoes.findById(id)  
                .orElseThrow(() -> new EntityNotFoundException("Publicacao nao encontrada"));
    }

    private Solicitacao buscarSolicitacao(Long id) {    
           
        return solicitacoes.findById(id)     
                .orElseThrow(() -> new EntityNotFoundException("Solicitacao nao encontrada"));
                      
            }         

    private void criarNotificacao(Usuario usuario, String mensagem) {     

        Notificacao notificacao = new Notificacao();

        notificacao.setUsuario(usuario);
                                                       
        notificacao.setMensagem(mensagem);     

        notificacoes.save(notificacao);             

    }

}
