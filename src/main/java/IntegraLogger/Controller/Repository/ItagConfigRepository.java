package IntegraLogger.Controller.Repository;

import IntegraLogger.Model.Tag.ItagConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItagConfigRepository extends JpaRepository<ItagConfig, Long> {
}
