package br.com.economiacircular.plataforma.repository;   
  
import br.com.economiacircular.plataforma.domain.Solicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface SolicitacaoRepository  extends JpaRepository< Solicitacao, Long>   {
    List<Solicitacao> findByPublicacaoDonoId (Long donoId);

}
