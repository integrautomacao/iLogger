package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.ItagConfigRepository;
import IntegraLogger.Model.Tag.ItagConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItagConfigService extends ServiceBase<ItagConfig, Long, ItagConfigRepository> {

    @Autowired
    public ItagConfigService(ItagConfigRepository repository) {
        super(repository);
    }

    public int countInt() {
        return (int)repository.count();
    }

    public ItagConfig getByName(String s){
        return repository.findByName(s);
    }
}
