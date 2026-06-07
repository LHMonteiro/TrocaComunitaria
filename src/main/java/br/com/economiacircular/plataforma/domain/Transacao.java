package br.com.economiacircular.plataforma.domain;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="transacoes")
public class Transacao{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 

    @ManyToOne
    @JoinColumn(name="publicacao_id")
    private  Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name="pagador_id")
    private Usuario pagador;

    @ManyToOne
    @JoinColumn(name="recebedor_id")
    private Usuario recebedor;

    private Integer creditos;

    private LocalDateTime criadaEm = LocalDateTime.now();

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