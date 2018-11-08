package IntegraLogger.Controller.Repository;

import IntegraLogger.Model.Tag.ItagDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItagDescRepository extends JpaRepository<ItagDescription, Long> {

    //    @Query("from * ")
//    ItagDescription findByNameAndSource(String name, Long plcId);
}
