
package br.com.economiacircular.plataforma.repository; 

import br.com.economiacircular.plataforma.domain.Notificacao;

import org.springframework.data.jpa.repository.JpaRepository;     
import java.util.List; 

public interface  NotificacaoRepository extends JpaRepository<Notificacao,  Long> {  
    List<Notificacao> findByUsuarioIdOrderByCriadaEmDesc(Long usuarioId);            
}