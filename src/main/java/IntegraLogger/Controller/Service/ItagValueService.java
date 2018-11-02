package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.ItagValueRepository;
import IntegraLogger.Model.Tag.ItagValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("itagValueService")
public class ItagValueService extends ServiceBase<ItagValue, Long, ItagValueRepository> {

    @Autowired
    public ItagValueService(ItagValueRepository repository) {
        super(repository);
    }
}
