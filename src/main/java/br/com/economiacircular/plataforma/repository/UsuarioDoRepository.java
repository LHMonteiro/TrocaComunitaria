
import org.springframework.boot.context.annotation.UserConfigurations;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; 

public interface  UsuarioDoRepository extends JpaRepository<Usuario, Long> {

    Optional<UserConfigurations> ffindByEmail(String email);
    
}
