package IntegraLogger.API;

import IntegraLogger.Controller.Service.ItagConfigService;
import IntegraLogger.Model.Tag.ItagConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tag")
public class ItagApi implements ApiBase<ItagConfig, Long> {

    @Autowired
    private ItagConfigService service;

    @Override
    @PostMapping(consumes = "application/json")
    @Transactional
    public ItagConfig save(@RequestBody ItagConfig obj) {
        return service.save(obj);
    }

    @Override
    @Transactional
    @PutMapping
    public Optional update(Long id, ItagConfig object) {
//        return service.update(id, object);
        return null;
    }

    @Override
    @GetMapping
    public List<ItagConfig> getAll() {
        return service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ItagConfig getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deletePermanent(id);
    }

}
