package IntegraLogger.Enviroment;

import IntegraLogger.Application.Listeners.ListenersIndex;
import IntegraLogger.Application.PlcThread;
import IntegraLogger.Application.ThreadPool;
import IntegraLogger.Controller.Service.*;
import IntegraLogger.Model.Plc.Plc;
import IntegraLogger.Model.Tag.ItagConfig;
import IntegraLogger.Model.Tag.ItagDescription;
import IntegraLogger.Model.User.Sector;
import IntegraLogger.Model.User.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private SectorService sectorService;

    @Autowired
    private EventLogService eventLogService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //just prepare the enviroment for develop running
        startupEnviroment();
        eventLogService.logRegister("Sistema iLogger foi iniciado!", "App Startup");
    }

    private void startupEnviroment() {
//        //TODO this is a temporary enviroment preparation
//
//
//
//
//        if (!plcService.hasData()) {
//            Plc plc = new Plc();
//
//
//            plc.setIp("192.168.0.100");
//
//
//            plc.setSlot(0);
//
//            plc.setDescription("CLP-Processo");
//            ItagDescription description1= new ItagDescription("Analógico 1");
//            ItagDescription description2= new ItagDescription("Analógico 2");
//            ItagDescription description3= new ItagDescription("Analógico 03");
//
//
//
//            ItagConfig itagConfig1 = new ItagConfig("Anlg1", description1, 1);
//            ItagConfig itagConfig2 = new ItagConfig("Anlg2", description2, 1);
//            ItagConfig itagConfig3 = new ItagConfig("Anlg3", description3, 1);
//
//
//
//            itagConfig1.setListener(ListenersIndex.PERSIST);
//            itagConfig2.setListener(ListenersIndex.PERSIST);
//            itagConfig3.setListener(ListenersIndex.PERSIST);
//            itagConfig4.setListener(ListenersIndex.PERSIST);
//            itagConfig5.setListener(ListenersIndex.PERSIST);
//            itagConfig6.setListener(ListenersIndex.PERSIST);
////            itagConfig7.setListener(ListenersIndex.PERSIST);
////            itagConfig8.setListener(ListenersIndex.PERSIST);
////            itagConfig9.setListener(ListenersIndex.PERSIST);
////            itagConfig10.setListener(ListenersIndex.PERSIST);
////            itagConfig11.setListener(ListenersIndex.PERSIST);
////            itagConfig12.setListener(ListenersIndex.PERSIST);
////            itagConfig13.setListener(ListenersIndex.PERSIST);
////            itagConfig14.setListener(ListenersIndex.PERSIST);
////            itagConfig15.setListener(ListenersIndex.PERSIST);
////            itagConfig16.setListener(ListenersIndex.PERSIST);
////            itagConfig17.setListener(ListenersIndex.PERSIST);
////            itagConfig18.setListener(ListenersIndex.PERSIST);
////            itagConfig19.setListener(ListenersIndex.PERSIST);
////            itagConfig20.setListener(ListenersIndex.PERSIST);
////            itagConfig21.setListener(ListenersIndex.PERSIST);
////            itagConfig22.setListener(ListenersIndex.PERSIST);
//            itagConfig23.setListener(ListenersIndex.PERSIST);
//            itagConfig24.setListener(ListenersIndex.PERSIST);
//            itagConfig25.setListener(ListenersIndex.PERSIST);
//
//            itagConfig1.setListener(ListenersIndex.EMAIL);
//            itagConfig2.setListener(ListenersIndex.EMAIL);
//            itagConfig3.setListener(ListenersIndex.EMAIL);
//            itagConfig4.setListener(ListenersIndex.EMAIL);
//            itagConfig5.setListener(ListenersIndex.EMAIL);
//            itagConfig6.setListener(ListenersIndex.EMAIL);
////            itagConfig7.setListener(ListenersIndex.EMAIL);
////            itagConfig8.setListener(ListenersIndex.EMAIL);
////            itagConfig9.setListener(ListenersIndex.EMAIL);
////            itagConfig10.setListener(ListenersIndex.EMAIL);
////            itagConfig11.setListener(ListenersIndex.EMAIL);
////            itagConfig12.setListener(ListenersIndex.EMAIL);
////            itagConfig13.setListener(ListenersIndex.EMAIL);
////            itagConfig14.setListener(ListenersIndex.EMAIL);
////            itagConfig15.setListener(ListenersIndex.EMAIL);
////            itagConfig16.setListener(ListenersIndex.EMAIL);
////            itagConfig17.setListener(ListenersIndex.EMAIL);
////            itagConfig18.setListener(ListenersIndex.EMAIL);
////            itagConfig19.setListener(ListenersIndex.EMAIL);
////            itagConfig20.setListener(ListenersIndex.EMAIL);
////            itagConfig21.setListener(ListenersIndex.EMAIL);
////            itagConfig22.setListener(ListenersIndex.EMAIL);
//            itagConfig23.setListener(ListenersIndex.EMAIL);
//            itagConfig24.setListener(ListenersIndex.EMAIL);
//            itagConfig25.setListener(ListenersIndex.EMAIL);
//
//
//
//            plc.getItagConfigs().add(itagConfig1);
//            plc.getItagConfigs().add(itagConfig2);
//            plc.getItagConfigs().add(itagConfig3);
//            plc.getItagConfigs().add(itagConfig4);
//            plc.getItagConfigs().add(itagConfig5);
//            plc.getItagConfigs().add(itagConfig6);
////            plc.getItagConfigs().add(itagConfig7);
////            plc.getItagConfigs().add(itagConfig8);
////            plc.getItagConfigs().add(itagConfig9);
////            plc.getItagConfigs().add(itagConfig10);
////            plc.getItagConfigs().add(itagConfig11);
////            plc.getItagConfigs().add(itagConfig12);
////            plc.getItagConfigs().add(itagConfig13);
////            plc.getItagConfigs().add(itagConfig14);
////            plc.getItagConfigs().add(itagConfig15);
////            plc.getItagConfigs().add(itagConfig16);
////            plc.getItagConfigs().add(itagConfig17);
////            plc.getItagConfigs().add(itagConfig18);
////            plc.getItagConfigs().add(itagConfig19);
////            plc.getItagConfigs().add(itagConfig20);
////            plc.getItagConfigs().add(itagConfig21);
////            plc.getItagConfigs().add(itagConfig22);
//            plc.getItagConfigs().add(itagConfig23);
//            plc.getItagConfigs().add(itagConfig24);
//            plc.getItagConfigs().add(itagConfig25);
//
//
//
//            itagConfigService.save(itagConfig1);
//            itagConfigService.save(itagConfig2);
//            itagConfigService.save(itagConfig3);
//            itagConfigService.save(itagConfig4);
//            itagConfigService.save(itagConfig5);
//            itagConfigService.save(itagConfig6);
////            itagConfigService.save(itagConfig7);
////            itagConfigService.save(itagConfig8);
////            itagConfigService.save(itagConfig9);
////            itagConfigService.save(itagConfig10);
////            itagConfigService.save(itagConfig11);
////            itagConfigService.save(itagConfig12);
////            itagConfigService.save(itagConfig13);
////            itagConfigService.save(itagConfig14);
////            itagConfigService.save(itagConfig15);
////            itagConfigService.save(itagConfig16);
////            itagConfigService.save(itagConfig17);
////            itagConfigService.save(itagConfig18);
////            itagConfigService.save(itagConfig19);
////            itagConfigService.save(itagConfig20);
////            itagConfigService.save(itagConfig21);
////            itagConfigService.save(itagConfig22);
//            itagConfigService.save(itagConfig23);
//            itagConfigService.save(itagConfig24);
//            itagConfigService.save(itagConfig25);
//
//
//
//            plcService.save(plc);
//
//        }
//
//        List<Plc> plcs = plcService.getAll();
//
//
//        PlcThread plcThread = new PlcThread(plcs.get(0));
//        pool.addPlcThread(plcThread);
//        System.out.println(plcThread);
//        Thread thread = new Thread(plcThread, plcs.get(0).getDescription());
//        pool.addThread(thread);
//        thread.start();
    }
}
