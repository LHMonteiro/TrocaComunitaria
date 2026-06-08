package br.com.economiacircular.plataforma.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitacoes")

public class Solicitacao {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)      
    private Long id;
      
         
    @ManyToOne     
    @JoinColumn(name="publicacao_id")         
    private Publicacao publicacao;    

          
    @ManyToOne
    @JoinColumn(name="interessado_id")           
    private Usuario interessado;      
      

    @Enumerated(EnumType.STRING)   
    private StatusSolicitacao  status = StatusSolicitacao.PENDENTE;    


    public void aceitar(Long usuarioId) {

        if (!publicacao.pertenceAo(usuarioId)) {

             throw new IllegalArgumentException("Somente o dono pode aceitar esta solicitacao");
        
            }
        
            if (this.status != StatusSolicitacao.PENDENTE) {
            throw new IllegalArgumentException("Esta solicitacao ja foi respondida");
        
        }      

    
     interessado.debitarSaldo(publicacao.getValorCreditos());

         publicacao.getDono().creditarSaldo(publicacao.getValorCreditos());
 
        // Atualiza estados
        publicacao.concluir();
        this.status = StatusSolicitacao.ACEITA;
    }

    public void recusar(Long usuarioId) {

        if (!publicacao.pertenceAo(usuarioId)) {
            
            throw new IllegalArgumentException("Somente o dono pode recusar esta solicitacao");
        
        }
        
        if (this.status != StatusSolicitacao.PENDENTE) {
        
            throw new IllegalArgumentException("Esta solicitacao ja foi respondida");
        
        }
       
        publicacao.disponibilizar();

        this.status = StatusSolicitacao.RECUSADA;
    }

     public String mensagemAceiteParaDono() {

        return "Troca concluida: " + publicacao.getTitulo()      
                + ". Contato do interessado: " + interessado.getContato();          
           
    }
 
    public String mensagemAceiteParaInteressado() {

        return "Troca concluida: " + publicacao.getTitulo()     
                + ". Contato do dono: " + publicacao.getDono().getContato();    
            
    }

    public String mensagemRecusa() {

        return "Sua solicitacao foi recusada: " + publicacao.getTitulo();       
             

    }
       
    public Long getId() {   
        return id; }     

              
    public void setId(Long id) {     
        this.id = id;      
    }


    public Publicacao getPublicacao() {      
     
        return publicacao; 
    }


    public void setPublicacao(Publicacao publicacao) {     
        this.publicacao = publicacao;     
    }

    public Usuario getInteressado() { 
        return interessado; }     

    public void setInteressado(Usuario   interessado) { 
        this.interessado = interessado; }   
    public StatusSolicitacao getStatus() {
         return status;    
        }
           
    public void setStatus(StatusSolicitacao status) {       
        this.status = status;     
    }     
}
