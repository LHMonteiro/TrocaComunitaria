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
