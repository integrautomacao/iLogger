package IntegraLogger.Controller.Repository;

import IntegraLogger.Model.Tag.ItagValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItagValueRepository extends JpaRepository<ItagValue, Long> {
}
