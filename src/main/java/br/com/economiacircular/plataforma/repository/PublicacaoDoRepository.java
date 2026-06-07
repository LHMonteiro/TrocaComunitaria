import br.com.economiacircular.plataforma.domain.Publicacao;
import br.com.economiacircular.plataforma.domain.StatusPublicacao;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PublicacaoDoRepository extends JpaRepository<Publicacao, Long> {
    List<Publicacao> findByStatusAndDonoIdNot(StatusPublicacao status, Long donoId);   
    
    List<Publicacao> findByDonoId(Long donoId);  
}  