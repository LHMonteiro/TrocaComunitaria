package br.com.economiacircular.plataforma.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.persistence.JoinColumn;

@Entity
@Table(name="notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="Usuario_id")
    private Usuario usuario;

    private String mensagem;

    private LocalDateTime  criadaEm = LocalDateTime.now();

    public Long getId() {
        
        return id; }

    public void setId(Long id) { 
        this.id = id; 

    }
    public Usuario getUsuario() { 
        return usuario; }

    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; }

    public String getMensagem() { 
        return mensagem; }

    public void setMensagem(String mensagem) { 
        this.mensagem = mensagem; }

    public LocalDateTime getCriadaEm() {
         return criadaEm; }

    public void setCriadaEm(LocalDateTime criadaEm) { 
        this.criadaEm = criadaEm; }
    

}
