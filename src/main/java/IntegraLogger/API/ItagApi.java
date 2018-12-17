package IntegraLogger.API;

import IntegraLogger.Controller.Service.ItagConfigService;
import IntegraLogger.Controller.Service.ServiceBase;
import IntegraLogger.Model.Tag.ItagConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class ItagApi extends ApiBase<ItagConfig, Long>{

    @Autowired
    public ItagApi(ItagConfigService serviceBase) {
        super(serviceBase);
    }
}
