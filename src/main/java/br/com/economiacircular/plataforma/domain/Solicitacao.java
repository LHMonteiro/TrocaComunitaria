import jakarta.persistence.*;

@Entity
@Table(name = "Solicitacao")

public class Solicitacao {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="Publicacao")
    private Publicacao publicacao;


    @ManyToOne
    @JoinColumn(name="interessado_id")
    private Usuario interesado;


    @Enumerated(EnumType.STRING)
    private StatusDaSolicitacao status = StatusDaSolicitacao.PENDENTE;

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

    public void setInteressado(Usuario interessado) { 
        this.interessado = interessado; }
    public StatusSolicitacao getStatus() {
         return status; 
        }

    public void setStatus(StatusSolicitacao status) { 
        this.status = status; 
    }
}
