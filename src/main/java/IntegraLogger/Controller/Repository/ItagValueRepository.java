package IntegraLogger.Controller.Repository;

import IntegraLogger.Model.Tag.ItagValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ItagValueRepository extends JpaRepository<ItagValue, Long> {

    @Transactional
    @Modifying
    @Query("update ItagValue value set value.lastUpdate = ?1 where value.id = ?2")
    void setTimeUpdate(Date date,Long id);

    List<ItagValue> getAllByDateAndType(String date, String type);

    ItagValue getTopByName(String name);
}
