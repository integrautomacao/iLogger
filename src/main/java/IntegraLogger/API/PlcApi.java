package IntegraLogger.API;

import IntegraLogger.Controller.Service.PlcService;
import IntegraLogger.DTO.PlcStatusDTO;
import IntegraLogger.Model.Plc.Plc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plc")
public class PlcApi implements ApiBase<Plc, Long> {

    @Autowired
    private PlcService plcService;

    @Override
    @PostMapping
    public Plc save(Plc o) {
        return plcService.save(o);
    }

    @Override
    @PutMapping
    public Optional update(Long aLong, Plc object) {
        return Optional.empty();
    }

    @Override
    @GetMapping
    public List<Plc> getAll() {
        return plcService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Plc getOne(@PathVariable Long id) {
        return plcService.getById(id);
    }

    @Override
    @DeleteMapping("/id")
    public void delete(@PathVariable Long id) {
        plcService.deletePermanent(id);
    }
}
