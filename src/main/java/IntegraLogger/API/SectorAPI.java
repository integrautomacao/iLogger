package IntegraLogger.API;

import IntegraLogger.Application.AppConstants;
import IntegraLogger.Controller.Service.SectorService;
import IntegraLogger.Model.User.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = AppConstants.FRONT_URL)
@RequestMapping("/sector")
public class SectorAPI implements ApiBase<Sector, Long> {

    @Autowired
    private SectorService sectorService;

    @Override
    @PostMapping
    public Sector save(Sector o) {
        return sectorService.save(o);
    }

    @Override
    public Optional update(Long aLong, Sector object) {
        return Optional.empty();
    }

    @Override
    @GetMapping
    public List<Sector> getAll() {
        return sectorService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Sector getOne(@PathParam("id") Long id) {
        return sectorService.getById(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathParam("id") Long id) {
        sectorService.deletePermanent(id);
    }

}
