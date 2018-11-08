package IntegraLogger.Enviroment;

import IntegraLogger.Application.Listeners.ListenersIndex;
import IntegraLogger.Application.PlcThread;
import IntegraLogger.Application.ThreadPool;
import IntegraLogger.Controller.Service.*;
import IntegraLogger.Model.Plc.Plc;
import IntegraLogger.Model.Tag.ItagConfig;
import IntegraLogger.Model.Tag.ItagDescription;
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
    private ThreadPool pool;

    @Autowired
    private UserService userService;

    @Autowired
    private ItagConfigService itagConfigService;

    @Autowired
    private ItagDescService itagDescService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //just prepare the enviroment for develop running
        startupEnviroment();
    }

    private void startupEnviroment() {
        //TODO this is a temporary enviroment preparation
        if (!userService.hasData()) {
            User user = new User();
            user.setName("William Douglas");
            user.setEmail("wdouglas@test.com");
            user.setPassword("TeSt");
            user.setPhoneNumber("1234567890");

            userService.save(user);

        }

        if (!plcService.hasData()) {
            Plc plc = new Plc();
            Plc plc2 = new Plc();
            plc.setIp("192.168.0.217");
            plc2.setIp("192.168.0.217");
            plc.setSlot(0);
            plc2.setSlot(0);
            plc.setDescription("PLC_Teste01");
            plc2.setDescription("PLC_Segundo");
            ItagDescription description1 = new ItagDescription("Tag Booleano de mudança rápida");
            ItagDescription description2 = new ItagDescription("PLC 2 - Alarme de Teste");
            ItagDescription description3 = new ItagDescription("Palavra lida");
            ItagDescription description4 = new ItagDescription("Valor acumulado de tempo");
            ItagDescription description5 = new ItagDescription("valor inteiro eu acho");
            ItagDescription description6 = new ItagDescription("Contador em segundos");
            ItagDescription description7 = new ItagDescription("PLC 2 - Variável real aleatória");
            itagDescService.save(description1);
            itagDescService.save(description2);
            itagDescService.save(description3);
            itagDescService.save(description4);
            itagDescService.save(description5);
            itagDescService.save(description6);
            itagDescService.save(description7);
            ItagConfig itagConfig1 = new ItagConfig("Bool1", description1, 1);
            ItagConfig itagConfig2 = new ItagConfig("AlarmTest", description2, 1);
            ItagConfig itagConfig3 = new ItagConfig("wstring", description3, 1);
            ItagConfig itagConfig4 = new ItagConfig("timmer1.ACC", description4, 5);
            ItagConfig itagConfig5 = new ItagConfig("wdouglas", description5, 2);
            ItagConfig itagConfig6 = new ItagConfig("xSecCounter", description6, 2);
            ItagConfig itagConfig7 = new ItagConfig("Body1HFlow", description7, 5);
            itagConfig1.setListener(ListenersIndex.PERSIST);
            itagConfig2.setListener(ListenersIndex.PERSIST);
            itagConfig3.setListener(ListenersIndex.PERSIST);
            itagConfig4.setListener(ListenersIndex.PERSIST);
            itagConfig5.setListener(ListenersIndex.PERSIST);
            itagConfig6.setListener(ListenersIndex.PERSIST);
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
        }

        Plc plc = plcService.getAll().get(0);
        PlcThread plcThread = new PlcThread(plc);

        pool.addPlcThread(plcThread);
        System.out.println(plcThread);
        Thread thread = new Thread(plcThread, plc.getDescription());
        pool.addThread(thread);
        thread.start();

        Plc plc2 = plcService.getAll().get(1);
        PlcThread plcThread2 = new PlcThread(plc2);
        pool.addPlcThread(plcThread2);
        System.out.println(plcThread2);
        Thread thread2 = new Thread(plcThread2, plc2.getDescription());
        pool.addThread(thread2);
        thread2.start();


    }
}
