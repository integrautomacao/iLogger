package IntegraLogger.API;

import IntegraLogger.Controller.Service.ItagValueService;
import IntegraLogger.Controller.Service.PublicService;
import IntegraLogger.DTO.PlcStatusDTO;
import IntegraLogger.DTO.TagValueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/public")
public class PublicApi {

    @Autowired
    private ItagValueService itagValueService;
    @Autowired
    private PublicService publicService;

    @GetMapping("/status")
    public List<PlcStatusDTO> getStatus() {
        return publicService.getConnectionStatus();
    }

    @GetMapping("/tagValue")
    public Set<TagValueDTO> getActiveTags(){
        return itagValueService.getActiveTags();
    }

    public PublicApi() {
    }
}
