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
        if (!sectorService.hasData()){
            Sector sector1 = new Sector();
            Sector sector2 = new Sector();
            Sector sector3 = new Sector();

            sector1.setName("Visualização");
            sector1.setLevel(1);

            sector2.setName("Operação");
            sector2.setLevel(2);

            sector3.setName("Administrador");
            sector3.setLevel(3);

            sectorService.save(sector1);
            sectorService.save(sector2);
            sectorService.save(sector3);
        }

        if (!userService.hasData()) {
            Usuario user = new Usuario();
            user.setName("William Douglas");
            user.setEmail("wdouglas@test.com");
            user.setPassword("1");
            user.setPhoneNumber(1234567890L);

            Usuario user1 = new Usuario();
            user1.setName("José da Silva");
            user1.setEmail("jose@silva.com");
            user1.setPassword("1234");
            user1.setPhoneNumber(1234567890L);


            Usuario user2 = new Usuario();
            user2.setName("Maria da Costa");
            user2.setEmail("maria@costa.com");
            user2.setPassword("1234");
            user2.setPhoneNumber(1234567890L);

            userService.save(user);
            userService.save(user1);
            userService.save(user2);

        }

        if (!plcService.hasData()) {
            Plc plc = new Plc();
            Plc plc20 = new Plc();
            Plc plc30 = new Plc();
//            plc.setIp("10.195.27.204");
            plc.setIp("192.168.0.101");
            plc20.setIp("10.44.21.20");
            plc30.setIp("10.44.21.30");
            plc20.setDescription("clp_superbac_1");
            plc30.setDescription("clp_superbac_seg");
            plc.setSlot(0);
            plc20.setSlot(0);
            plc30.setSlot(0);
            plc.setDescription("CLP_ADM");
            ItagDescription description1 = new ItagDescription("BICA 1 DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO");
            ItagDescription description2 = new ItagDescription("BICA 2 DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO");
            ItagDescription description3 = new ItagDescription("BICA 3 DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO");
            ItagDescription description4 = new ItagDescription("BICA 4 DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO");
            ItagDescription description5 = new ItagDescription("CANCELA DE ENTRADA DO CARREGAMENTO FOI MOVIMENTADA SEM NENHUM TICKET DE CARREGAMENTO");
            ItagDescription description6 = new ItagDescription("CANCELA DE SAÍDA DO CARREGAMENTO FOI MOVIMENTADA SEM NENHUM TICKET DE CARREGAMENTO");
            ItagDescription description7 = new ItagDescription("A FUNÇÃO ACERTO DE CARGA NO SISTEMA FOI HABILITADA");
            ItagDescription description8 = new ItagDescription("CARREGAMENTO FINALIZADO PELO SISTEMA DO CARREGAMENTO");
            ItagDescription description9 = new ItagDescription("DETECTADO MOVIMENTAÇÃO DE PESO NA BALANÇA SEM TICKET DE CARREGAMENTO");
            ItagDescription description10 = new ItagDescription("QUANTIDADE DE CAMINHÕES CARREGADOS DIA");
            ItagDescription description11 = new ItagDescription("QUANTIDADE DE CAMINHÕES (TICKETS) CANCELADOS DIA");
            ItagDescription description12 = new ItagDescription("TRIGGER PARA DISPARO DE RELATORIO");
            ItagDescription description13 = new ItagDescription("TAG PLC 20");
            ItagDescription description14 = new ItagDescription("TAG PLC SEGURANÇA");

            ItagConfig itagConfig1 = new ItagConfig("BICA1_ABERTA_MANUAL", description1, 1);
            ItagConfig itagConfig2 = new ItagConfig("BICA2_ABERTA_MANUAL", description2, 1);
            ItagConfig itagConfig3 = new ItagConfig("BICA3_ABERTA_MANUAL", description3, 1);
            ItagConfig itagConfig4 = new ItagConfig("BICA4_ABERTA_MANUAL", description4, 1);
            ItagConfig itagConfig5 = new ItagConfig("CA01_ABERTA_MANUAL", description5, 1);
            ItagConfig itagConfig6 = new ItagConfig("CA02_ABERTA_MANUAL", description6, 1);
            ItagConfig itagConfig7 = new ItagConfig("ACERTO_CARGA", description7, 1);
            ItagConfig itagConfig8 = new ItagConfig("CARREGAMENTO_REPROVADO", description8, 1);
            ItagConfig itagConfig9 = new ItagConfig("PESO_BALANCA_SEM_TICKET", description9, 1);
            ItagConfig itagConfig10 = new ItagConfig("CTU_CAMINHOES_CARREGADOS.ACC", description10, 5);
            ItagConfig itagConfig11 = new ItagConfig("CTU_CAMINHOES_CARREGADOS_C.ACC", description11, 5);
            ItagConfig itagConfig12 = new ItagConfig("TRIGGER_RELATORIO_DIARIO", description12, 1);
            ItagConfig itagConfig13 = new ItagConfig("EMERGENCIA_CCM", description13, 1);
            ItagConfig itagConfig14 = new ItagConfig("XV_desviadora", description14, 1);
            itagConfig1.setListener(ListenersIndex.PERSIST);
            itagConfig1.setListener(ListenersIndex.EMAIL);
            itagConfig2.setListener(ListenersIndex.PERSIST);
            itagConfig2.setListener(ListenersIndex.EMAIL);
            itagConfig3.setListener(ListenersIndex.PERSIST);
            itagConfig3.setListener(ListenersIndex.EMAIL);
            itagConfig4.setListener(ListenersIndex.PERSIST);
            itagConfig4.setListener(ListenersIndex.EMAIL);
            itagConfig5.setListener(ListenersIndex.PERSIST);
            itagConfig5.setListener(ListenersIndex.EMAIL);
            itagConfig6.setListener(ListenersIndex.PERSIST);
            itagConfig6.setListener(ListenersIndex.EMAIL);
            itagConfig7.setListener(ListenersIndex.PERSIST);
            itagConfig7.setListener(ListenersIndex.EMAIL);
            itagConfig8.setListener(ListenersIndex.PERSIST);
            itagConfig8.setListener(ListenersIndex.EMAIL);
            itagConfig9.setListener(ListenersIndex.PERSIST);
            itagConfig9.setListener(ListenersIndex.EMAIL);
            itagConfig10.setListener(ListenersIndex.PERSIST);
            itagConfig11.setListener(ListenersIndex.PERSIST);
            itagConfig12.setListener(ListenersIndex.TRIGGER);
            itagConfig13.setListener(ListenersIndex.PERSIST);
            itagConfig14.setListener(ListenersIndex.PERSIST);


            plc.getItagConfigs().add(itagConfig1);
            plc.getItagConfigs().add(itagConfig2);
            plc.getItagConfigs().add(itagConfig3);
            plc.getItagConfigs().add(itagConfig4);
            plc.getItagConfigs().add(itagConfig5);
            plc.getItagConfigs().add(itagConfig6);
            plc.getItagConfigs().add(itagConfig7);
            plc.getItagConfigs().add(itagConfig8);
            plc.getItagConfigs().add(itagConfig9);
            plc.getItagConfigs().add(itagConfig10);
            plc.getItagConfigs().add(itagConfig11);
            plc.getItagConfigs().add(itagConfig12);

            plc20.getItagConfigs().add(itagConfig14);
            plc30.getItagConfigs().add(itagConfig13);

            itagConfigService.save(itagConfig1);
            itagConfigService.save(itagConfig2);
            itagConfigService.save(itagConfig3);
            itagConfigService.save(itagConfig4);
            itagConfigService.save(itagConfig5);
            itagConfigService.save(itagConfig6);
            itagConfigService.save(itagConfig7);
            itagConfigService.save(itagConfig8);
            itagConfigService.save(itagConfig9);
            itagConfigService.save(itagConfig10);
            itagConfigService.save(itagConfig11);
            itagConfigService.save(itagConfig12);
            itagConfigService.save(itagConfig13);
            itagConfigService.save(itagConfig14);

            plcService.save(plc);
            plcService.save(plc20);
            plcService.save(plc30);
        }

        List<Plc> plcs = plcService.getAll();

//        for (Plc plc : plcs){
//            PlcThread plcThread = new PlcThread(plc);
//            pool.addPlcThread(plcThread);
//            System.out.println(plcThread);
//            Thread thread = new Thread(plcThread, plc.getDescription());
//            pool.addThread(thread);
//            thread.start();
//        }




    }
}
