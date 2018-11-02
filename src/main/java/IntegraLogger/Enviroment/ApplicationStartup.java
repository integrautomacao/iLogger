package IntegraLogger.Enviroment;

import IntegraLogger.Application.Listeners.ListenersIndex;
import IntegraLogger.Application.PlcThread;
import IntegraLogger.Application.ThreadPool;
import IntegraLogger.Controller.Service.ItagConfigService;
import IntegraLogger.Controller.Service.PlcService;
import IntegraLogger.Controller.Service.UserService;
import IntegraLogger.Controller.Service.testeService;
import IntegraLogger.Model.Plc.Plc;
import IntegraLogger.Model.Tag.ItagConfig;
import IntegraLogger.Model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PlcService plcService;

    @Autowired
    private ThreadPool pool;

    @Autowired
    private UserService userService;

    @Autowired
    private ItagConfigService itagConfigService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //just prepare the enviroment for develop running
            startupEnviroment();
    }

    private void startupEnviroment() {
        //TODO this is a temporary enviroment preparation
        User user = new User();
        user.setName("William Douglas");
        user.setEmail("wdouglas@test.com");
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
        itagConfig1.setListener(ListenersIndex.EMAIL);
        itagConfig7.setListener(ListenersIndex.PERSIST);
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

        PlcThread plcThread = new PlcThread(plc);

        pool.addPlcThread(plcThread);
        System.out.println(plcThread);
        Thread thread = new Thread(plcThread, plc.getDescription());
        pool.addThread(thread);
        thread.start();

        PlcThread plcThread2 = new PlcThread(plc2);
        pool.addPlcThread(plcThread2);
        System.out.println(plcThread2);
        Thread thread2 = new Thread(plcThread2, plc2.getDescription());
        pool.addThread(thread2);
        thread2.start();


    }
}
