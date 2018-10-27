package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.ItagConfigRepository;
import IntegraLogger.Model.Tag.ItagConfig;
import org.springframework.stereotype.Service;

@Service
public class ItagConfigService extends ServiceBase<ItagConfig, Long, ItagConfigRepository> {
    public ItagConfigService(ItagConfigRepository repository) {
        super(repository);
    }
}
