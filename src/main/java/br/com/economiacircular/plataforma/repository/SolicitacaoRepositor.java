
import br.com.economiacircular.plataforma.domain.Solicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface SolicitacaoRepositor {
    List<Solicitacao> findByPublicacaoDonoId(Long donoId);

}
