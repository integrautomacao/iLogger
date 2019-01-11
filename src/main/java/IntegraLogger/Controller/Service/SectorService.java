package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.SectorRepository;
import IntegraLogger.Model.User.Sector;
import org.springframework.stereotype.Service;

@Service
public class SectorService extends ServiceBase<Sector, Long, SectorRepository> {
    public SectorService(SectorRepository repository) {
        super(repository);
    }

    @Override
    public Sector getById(Long id) {
        return repository.getOne(id);
    }
}
