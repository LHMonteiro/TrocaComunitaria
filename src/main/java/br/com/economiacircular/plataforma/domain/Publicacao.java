package br.com.economiacircular.plataforma.domain;  
   
import jakarta.persistence.*;

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
