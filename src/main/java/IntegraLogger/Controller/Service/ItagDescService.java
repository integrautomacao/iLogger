package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.ItagDescRepository;
import IntegraLogger.Model.Tag.ItagDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ItagDescService extends ServiceBase<ItagDescription, Long, ItagDescRepository> {
    public ItagDescService(ItagDescRepository repository) {
        super(repository);
    }

}
