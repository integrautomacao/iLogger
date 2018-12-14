package IntegraLogger.API;

import IntegraLogger.Controller.Service.ItagValueService;
import IntegraLogger.Controller.Service.PublicService;
import IntegraLogger.DTO.PlcStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicApi {

    @Autowired
    private ItagValueService itagValueService;
    @Autowired
    private PublicService publicService;
//
    @GetMapping("/status")
    public List<PlcStatusDTO> getStatus() {
        return publicService.getConnectionStatus();
    }

//    @GetMapping("/status")
//    public List<Thread> getStatus() {
//        return ThreadPool.getThreads();
//    }

    public PublicApi() {
    }
}
