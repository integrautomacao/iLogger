package IntegraLogger.Controller.Repository;

import IntegraLogger.Model.User.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    public List<Usuario> findAllByName(String name);
//    public User findById(Long id);

    public Usuario findByEmail(String email);

    public Usuario getById(Long id);
}
