package IntegraLogger.Controller.Repository;

import IntegraLogger.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAllByName(String name);
    public User findById(Long id);

}
