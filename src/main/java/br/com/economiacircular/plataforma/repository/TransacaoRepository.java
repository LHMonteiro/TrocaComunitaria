import br.com.economiacircular.plataforma.domain.Transicao;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransacaoRepository  {
    
     List<Transacao> findByPagadorIdOrRecebedorIdOrderByCriadaEmDesc(Long pagadorId, Long recebedorId);

}
