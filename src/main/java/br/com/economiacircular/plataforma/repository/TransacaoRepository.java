package br.com.economiacircular.plataforma.repository;

import br.com.economiacircular.plataforma.domain.Transacao;   


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
     List<Transacao> findByPagadorIdOrRecebedorIdOrderByCriadaEmDesc(Long pagadorId, Long recebedorId);

}
