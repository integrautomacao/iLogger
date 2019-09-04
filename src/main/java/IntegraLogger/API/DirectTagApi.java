package IntegraLogger.API;

import IntegraLogger.Controller.Service.DirectTagService;
import IntegraLogger.DTO.CIPDataDTO;
import IntegraLogger.DTO.MultipleTagReadDTO;
import IntegraLogger.Model.Plc.Plc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direct")
public class DirectTagApi {

    @Autowired
    private DirectTagService directTagService;

    @GetMapping("/getOneValue")
    public CIPDataDTO getOneValue(@RequestBody Plc plc, @RequestHeader String tag) {
        CIPDataDTO value = directTagService.getDirectValue(plc, tag);
        if (value != null) {
            return value;
        } else {
            //TODO tratar aqui a reposta de falha na request
            return null;
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getValues")
    public List<CIPDataDTO> getValuesFromArray(@RequestBody MultipleTagReadDTO data){
        return directTagService.getValuesFromArray(data);
    }
}
