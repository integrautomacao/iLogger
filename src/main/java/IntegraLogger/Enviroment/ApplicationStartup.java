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
        //TODO this is a temporary enviroment preparation




        if (!plcService.hasData()) {
            Plc plc = new Plc();


            plc.setIp("10.44.21.20");


            plc.setSlot(0);

            plc.setDescription("CLP-Processo");
            ItagDescription description1= new ItagDescription("PELETIZADORA 01");
            ItagDescription description2= new ItagDescription("PELETIZADORA 02");
            ItagDescription description3= new ItagDescription("PELETIZADORA 03");
            ItagDescription description4= new ItagDescription("CONDICIONADOR DA PELETIZADORA 01");
            ItagDescription description5= new ItagDescription("CONDICIONADOR DA PELETIZADORA 02");
            ItagDescription description6= new ItagDescription("CONDICIONADOR DA PELETIZADORA 03");
            ItagDescription description7= new ItagDescription("REDLER 06");
            ItagDescription description8= new ItagDescription("ELEVADOR 01");
            ItagDescription description9= new ItagDescription("ELEVADOR 02");
            ItagDescription description10= new ItagDescription("ELEVADOR 03");
            ItagDescription description11= new ItagDescription("ESTEIRA EXTRATORA DE PELLET DA LINHA 05");
            ItagDescription description12= new ItagDescription("REDLER 03");
            ItagDescription description13= new ItagDescription("REDLER 05");
            ItagDescription description14= new ItagDescription("REDLER 04");
            ItagDescription description15= new ItagDescription("ELEVADOR 04");
            ItagDescription description16= new ItagDescription("ESTEIRA DE ALIMENTAÇÃO DO SECADOR");
            ItagDescription description17= new ItagDescription("REDLER 01");
            ItagDescription description18= new ItagDescription("ESTEIRA DE RETORNO DA PENEIRA");
            ItagDescription description19= new ItagDescription("ESTEIRA DE ALIMENTAÇÃO");
            ItagDescription description20= new ItagDescription("REDLER 02");
            ItagDescription description21= new ItagDescription("PENEIRA 2 DO SECADOR");
            ItagDescription description22= new ItagDescription("PENEIRA 1 DO SECADOR");
            ItagDescription description23= new ItagDescription("ALIMENTADOR DA PELETIZADORA 01");
            ItagDescription description24= new ItagDescription("ALIMENTADOR DA PELETIZADORA 02");
            ItagDescription description25= new ItagDescription("ALIMENTADOR DA PELETIZADORA 03");



            ItagConfig itagConfig1 = new ItagConfig("M01_05PE01A", description1, 1);
            ItagConfig itagConfig2 = new ItagConfig("M02_05PE01B", description2, 1);
            ItagConfig itagConfig3 = new ItagConfig("M03_05PE01C", description3, 1);
            ItagConfig itagConfig4 = new ItagConfig("M12_05CO01A", description4, 1);
            ItagConfig itagConfig5 = new ItagConfig("M13_05CO01B", description5, 1);
            ItagConfig itagConfig6 = new ItagConfig("M14_05CO01C", description6, 1);
            ItagConfig itagConfig7 = new ItagConfig("M15_05TR06", description7, 1);
            ItagConfig itagConfig8 = new ItagConfig("M16_05EL01", description8, 1);
            ItagConfig itagConfig9 = new ItagConfig("M17_05EL02", description9, 1);
            ItagConfig itagConfig10 = new ItagConfig("M18_05EL03", description10, 1);
            ItagConfig itagConfig11 = new ItagConfig("M19_05TC02", description11, 1);
            ItagConfig itagConfig12 = new ItagConfig("M22_05TR03", description12, 1);
            ItagConfig itagConfig13 = new ItagConfig("M23_05TR05", description13, 1);
            ItagConfig itagConfig14 = new ItagConfig("M24_05TR04", description14, 1);
            ItagConfig itagConfig15 = new ItagConfig("M26_05EL04", description15, 1);
            ItagConfig itagConfig16 = new ItagConfig("M27_05TC03", description16, 1);
            ItagConfig itagConfig17 = new ItagConfig("M28_05TR01", description17, 1);
            ItagConfig itagConfig18 = new ItagConfig("M38_05TC04", description18, 1);
            ItagConfig itagConfig19 = new ItagConfig("M39_05TC01", description19, 1);
            ItagConfig itagConfig20 = new ItagConfig("M40_05TR02", description20, 1);
            ItagConfig itagConfig21 = new ItagConfig("M50_05PN03B", description21, 1);
            ItagConfig itagConfig22 = new ItagConfig("M51_05PN03A", description22, 1);
            ItagConfig itagConfig23 = new ItagConfig("M52_05AL01A", description23, 1);
            ItagConfig itagConfig24 = new ItagConfig("M53_05AL01B", description24, 1);
            ItagConfig itagConfig25 = new ItagConfig("M54_05AL01C", description25, 1);


            itagConfig1.setListener(ListenersIndex.PERSIST);
            itagConfig2.setListener(ListenersIndex.PERSIST);
            itagConfig3.setListener(ListenersIndex.PERSIST);
            itagConfig4.setListener(ListenersIndex.PERSIST);
            itagConfig5.setListener(ListenersIndex.PERSIST);
            itagConfig6.setListener(ListenersIndex.PERSIST);
//            itagConfig7.setListener(ListenersIndex.PERSIST);
//            itagConfig8.setListener(ListenersIndex.PERSIST);
//            itagConfig9.setListener(ListenersIndex.PERSIST);
//            itagConfig10.setListener(ListenersIndex.PERSIST);
//            itagConfig11.setListener(ListenersIndex.PERSIST);
//            itagConfig12.setListener(ListenersIndex.PERSIST);
//            itagConfig13.setListener(ListenersIndex.PERSIST);
//            itagConfig14.setListener(ListenersIndex.PERSIST);
//            itagConfig15.setListener(ListenersIndex.PERSIST);
//            itagConfig16.setListener(ListenersIndex.PERSIST);
//            itagConfig17.setListener(ListenersIndex.PERSIST);
//            itagConfig18.setListener(ListenersIndex.PERSIST);
//            itagConfig19.setListener(ListenersIndex.PERSIST);
//            itagConfig20.setListener(ListenersIndex.PERSIST);
//            itagConfig21.setListener(ListenersIndex.PERSIST);
//            itagConfig22.setListener(ListenersIndex.PERSIST);
            itagConfig23.setListener(ListenersIndex.PERSIST);
            itagConfig24.setListener(ListenersIndex.PERSIST);
            itagConfig25.setListener(ListenersIndex.PERSIST);

            itagConfig1.setListener(ListenersIndex.EMAIL);
            itagConfig2.setListener(ListenersIndex.EMAIL);
            itagConfig3.setListener(ListenersIndex.EMAIL);
            itagConfig4.setListener(ListenersIndex.EMAIL);
            itagConfig5.setListener(ListenersIndex.EMAIL);
            itagConfig6.setListener(ListenersIndex.EMAIL);
//            itagConfig7.setListener(ListenersIndex.EMAIL);
//            itagConfig8.setListener(ListenersIndex.EMAIL);
//            itagConfig9.setListener(ListenersIndex.EMAIL);
//            itagConfig10.setListener(ListenersIndex.EMAIL);
//            itagConfig11.setListener(ListenersIndex.EMAIL);
//            itagConfig12.setListener(ListenersIndex.EMAIL);
//            itagConfig13.setListener(ListenersIndex.EMAIL);
//            itagConfig14.setListener(ListenersIndex.EMAIL);
//            itagConfig15.setListener(ListenersIndex.EMAIL);
//            itagConfig16.setListener(ListenersIndex.EMAIL);
//            itagConfig17.setListener(ListenersIndex.EMAIL);
//            itagConfig18.setListener(ListenersIndex.EMAIL);
//            itagConfig19.setListener(ListenersIndex.EMAIL);
//            itagConfig20.setListener(ListenersIndex.EMAIL);
//            itagConfig21.setListener(ListenersIndex.EMAIL);
//            itagConfig22.setListener(ListenersIndex.EMAIL);
            itagConfig23.setListener(ListenersIndex.EMAIL);
            itagConfig24.setListener(ListenersIndex.EMAIL);
            itagConfig25.setListener(ListenersIndex.EMAIL);



            plc.getItagConfigs().add(itagConfig1);
            plc.getItagConfigs().add(itagConfig2);
            plc.getItagConfigs().add(itagConfig3);
            plc.getItagConfigs().add(itagConfig4);
            plc.getItagConfigs().add(itagConfig5);
            plc.getItagConfigs().add(itagConfig6);
//            plc.getItagConfigs().add(itagConfig7);
//            plc.getItagConfigs().add(itagConfig8);
//            plc.getItagConfigs().add(itagConfig9);
//            plc.getItagConfigs().add(itagConfig10);
//            plc.getItagConfigs().add(itagConfig11);
//            plc.getItagConfigs().add(itagConfig12);
//            plc.getItagConfigs().add(itagConfig13);
//            plc.getItagConfigs().add(itagConfig14);
//            plc.getItagConfigs().add(itagConfig15);
//            plc.getItagConfigs().add(itagConfig16);
//            plc.getItagConfigs().add(itagConfig17);
//            plc.getItagConfigs().add(itagConfig18);
//            plc.getItagConfigs().add(itagConfig19);
//            plc.getItagConfigs().add(itagConfig20);
//            plc.getItagConfigs().add(itagConfig21);
//            plc.getItagConfigs().add(itagConfig22);
            plc.getItagConfigs().add(itagConfig23);
            plc.getItagConfigs().add(itagConfig24);
            plc.getItagConfigs().add(itagConfig25);



            itagConfigService.save(itagConfig1);
            itagConfigService.save(itagConfig2);
            itagConfigService.save(itagConfig3);
            itagConfigService.save(itagConfig4);
            itagConfigService.save(itagConfig5);
            itagConfigService.save(itagConfig6);
//            itagConfigService.save(itagConfig7);
//            itagConfigService.save(itagConfig8);
//            itagConfigService.save(itagConfig9);
//            itagConfigService.save(itagConfig10);
//            itagConfigService.save(itagConfig11);
//            itagConfigService.save(itagConfig12);
//            itagConfigService.save(itagConfig13);
//            itagConfigService.save(itagConfig14);
//            itagConfigService.save(itagConfig15);
//            itagConfigService.save(itagConfig16);
//            itagConfigService.save(itagConfig17);
//            itagConfigService.save(itagConfig18);
//            itagConfigService.save(itagConfig19);
//            itagConfigService.save(itagConfig20);
//            itagConfigService.save(itagConfig21);
//            itagConfigService.save(itagConfig22);
            itagConfigService.save(itagConfig23);
            itagConfigService.save(itagConfig24);
            itagConfigService.save(itagConfig25);



            plcService.save(plc);

        }

        List<Plc> plcs = plcService.getAll();


        PlcThread plcThread = new PlcThread(plcs.get(0));
        pool.addPlcThread(plcThread);
        System.out.println(plcThread);
        Thread thread = new Thread(plcThread, plcs.get(0).getDescription());
        pool.addThread(thread);
        thread.start();
    }
}
