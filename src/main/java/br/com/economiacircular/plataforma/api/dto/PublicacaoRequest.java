package br.com.economiacircular.plataforma.api.dto;  

import org.hibernate.boot.beanvalidation.IntegrationException;

public class PublicacaoRequest {
    public String titulo;
    public String descricao;
    public String tipo;
    public Integer valorCreditos;
    public Long usuarioId;
    
}
