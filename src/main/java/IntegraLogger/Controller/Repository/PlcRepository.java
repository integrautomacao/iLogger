package IntegraLogger.Controller.Repository;

import IntegraLogger.Model.Plc.Plc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlcRepository extends JpaRepository<Plc, Long> {
}
