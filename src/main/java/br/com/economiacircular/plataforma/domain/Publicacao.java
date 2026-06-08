package br.com.economiacircular.plataforma.domain;  
   
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@AggregateRoot(descricao = "Raiz do agregado de trocas. Controla o ciclo de vida das Solicitacoes.")
@Entity
@Table(name = "publicacoes")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String tipo;
    private String descricao;
    private Integer valorCreditos;


     @Enumerated(EnumType.STRING)
    private StatusPublicacao status = StatusPublicacao.DISPONIVEL;

     @ManyToOne
    @JoinColumn(name = "dono_id")
    private Usuario dono;


    public void reservar() {

        if (this.status != StatusPublicacao.DISPONIVEL) {
             
            throw new IllegalArgumentException("Esta publicacao nao esta disponivel");
        
        }

        this.status = StatusPublicacao.RESERVADA;
    }


    public void concluir() {

        this.status = StatusPublicacao.CONCLUIDA;
    }

    public void disponibilizar() {
        this.status = StatusPublicacao.DISPONIVEL;
    }


    public void cancelar(Long usuarioId) {
        
        if (!pertenceAo(usuarioId)) {

             throw new IllegalArgumentException("Somente o dono pode cancelar esta publicacao");
        }
        
        this.status = StatusPublicacao.CANCELADA;
    }

    public boolean pertenceAo(Long usuarioId) {

         return this.dono.getId().equals(usuarioId);
    
        }


     public void validarSolicitante(Usuario solicitante) {
        
        if (pertenceAo(solicitante.getId())) {
             
            throw new IllegalArgumentException("Voce nao pode solicitar sua propria publicacao");
        
        }
        
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) {
         this.id = id; 
        }

    public String getTitulo() { 
        return titulo; }

    public void setTitulo(String titulo) { 
        this.titulo = titulo; }

    public String getDescricao() { 
        return descricao; 
    }

    public void setDescricao(String descricao) { 
        this.descricao = descricao; }

    public String getTipo() { 
        return tipo; 
    }

    public void setTipo(String tipo) { 
        this.tipo = tipo; }

    public Integer getValorCreditos() {
         return valorCreditos; }

    public void setValorCreditos(Integer valorCreditos) { 
        this.valorCreditos = valorCreditos; }

    public StatusPublicacao getStatus() { 
        return status; 
    }
    public void setStatus(StatusPublicacao status) { 
        this.status = status; }

    public Usuario getDono() { 
        return dono; }

    public void setDono(Usuario dono) { 
        this.dono = dono; }
    
}
