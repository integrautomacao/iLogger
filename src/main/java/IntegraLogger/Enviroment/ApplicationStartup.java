package IntegraLogger.Enviroment;

import IntegraLogger.Application.PlcConnection;
import IntegraLogger.Controller.Service.ItagConfigService;
import IntegraLogger.Controller.Service.PlcService;
import IntegraLogger.Controller.Service.UserService;
import IntegraLogger.Model.Plc.Plc;
import IntegraLogger.Model.Tag.ItagConfig;
import IntegraLogger.Model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PlcService plcService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItagConfigService itagConfigService;

    @Autowired
    private PlcConnection plcConnection;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //just prepare the enviroment for develop running
        startupEnviroment();


        plcConnection.createConnecion();


    }

    private void startupEnviroment(){
        //TODO this is a temporary enviroment preparation
        User user = new User();
        user.setName("William Douglas Costa Silva");
        user.setEmail("wdouglascosta@outlook.com");
        user.setPassword("TeSt");
        user.setPhoneNumber("1234567890");
        userService.save(user);
        Plc plc = new Plc();
        Plc plc2 = new Plc();
        plc.setIp("192.168.0.217");
        plc2.setIp("192.168.0.217");
        plc.setSlot(0);
        plc2.setSlot(0);
        plc.setDescription("PLC_Teste01");
        plc2.setDescription("PLC_Segundo");

        ItagConfig itagConfig1 = new ItagConfig("Bool1", "Tag Booleano de mudança rápida", 1);
        ItagConfig itagConfig2 = new ItagConfig("AlarmTest", "PLC 2 - Alarme de Teste", 1);
        ItagConfig itagConfig3 = new ItagConfig("wstring", "Palavra lida", 1);
        ItagConfig itagConfig4 = new ItagConfig("timmer1.ACC", "Valor acumulado de tempo", 5);
        ItagConfig itagConfig5 = new ItagConfig("wdouglas", "valor inteiro eu acho", 2);
        ItagConfig itagConfig6 = new ItagConfig("xSecCounter", "Contador em segundos", 2);
        ItagConfig itagConfig7 = new ItagConfig("Body1HFlow", "PLC 2 - Variável real aleatória", 5);

        plc.getItagConfigs().add(itagConfig1);
        plc2.getItagConfigs().add(itagConfig2);
        plc.getItagConfigs().add(itagConfig3);
        plc.getItagConfigs().add(itagConfig4);
        plc.getItagConfigs().add(itagConfig5);
        plc.getItagConfigs().add(itagConfig6);
        plc2.getItagConfigs().add(itagConfig7);

        itagConfigService.save(itagConfig1);
        itagConfigService.save(itagConfig2);
        itagConfigService.save(itagConfig3);
        itagConfigService.save(itagConfig4);
        itagConfigService.save(itagConfig5);
        itagConfigService.save(itagConfig6);
        itagConfigService.save(itagConfig7);

        plcService.save(plc);
        plcService.save(plc2);
    }
}
