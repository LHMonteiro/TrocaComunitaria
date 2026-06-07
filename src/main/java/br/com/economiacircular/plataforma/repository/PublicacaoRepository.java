package br.com.economiacircular.plataforma.repository;   
  
import br.com.economiacircular.plataforma.domain.Publicacao;
import br.com.economiacircular.plataforma.domain.StatusPublicacao;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
    List<Publicacao> findByStatusAndDonoIdNot(StatusPublicacao status, Long donoId);   
    
    List<Publicacao> findByDonoId(Long donoId);  
}  