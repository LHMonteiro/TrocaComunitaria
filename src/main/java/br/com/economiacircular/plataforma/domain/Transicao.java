import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="Transicao")
public class Transicao{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 

    @ManyToOne
    @JoinColumn(name="publicao_id")
    private  Publicacao publicao;


    @ManyToOne
    @JoinColumn(name="recebedor_id")
    private Usuario recebedor;


    private Integer credito;

    private LocalDateTime CriadaEm = LocalDateTime.now();

    public Long getId() { 
        return id; }

    public void setId(Long id) { 
        this.id = id; }
    
    public Publicacao getPublicacao() { 
        return publicacao; }

    public void setPublicacao(Publicacao publicacao) { 
        this.publicacao = publicacao; }

    
    public Usuario getPagador() { 
        return pagador; }

    public void setPagador(Usuario pagador) { 
        this.pagador = pagador; }

    public Usuario getRecebedor() { 
        return recebedor; }

    public void setRecebedor(Usuario recebedor) { 
        this.recebedor = recebedor; }

    public Integer getCreditos() { 
        return creditos; }

    public void setCreditos(Integer creditos) { 
        this.creditos = creditos; }

    
    public LocalDateTime getCriadaEm() { 
        return criadaEm; }

    public void setCriadaEm(LocalDateTime criadaEm) { 
        this.criadaEm = criadaEm; }


}