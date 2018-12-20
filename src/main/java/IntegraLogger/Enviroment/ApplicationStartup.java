package IntegraLogger.Enviroment;

import IntegraLogger.Application.Listeners.ListenersIndex;
import IntegraLogger.Application.PlcThread;
import IntegraLogger.Application.ThreadPool;
import IntegraLogger.Controller.Service.*;
import IntegraLogger.Model.Plc.Plc;
import IntegraLogger.Model.Tag.ItagConfig;
import IntegraLogger.Model.Tag.ItagDescription;
import IntegraLogger.Model.User.Usuario;
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
            Usuario user = new Usuario();
            user.setName("William Douglas");
            user.setEmail("wdouglas@test.com");
            user.setPassword("TeSt");
            user.setPhoneNumber("1234567890");

            userService.save(user);

        }

        if (!plcService.hasData()) {
            Plc plc = new Plc();
            plc.setIp("10.195.27.204");
//            plc.setIp("192.168.0.101");
            plc.setSlot(0);
            plc.setDescription("CLP_ADM");
            ItagDescription description1 = new ItagDescription("BICA 1 DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO ATIVO");
            ItagDescription description2 = new ItagDescription("BICA 2 DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO ATIVO");
            ItagDescription description3 = new ItagDescription("BICA 3 DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO ATIVO");
            ItagDescription description4 = new ItagDescription("BICA 4 DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO ATIVO");
            ItagDescription description5 = new ItagDescription("CANCELA DE ENTRADA DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO ATIVO");
            ItagDescription description6 = new ItagDescription("CANCELA DE SAÍDA DO CARREGAMENTO FOI ABERTA SEM NENHUM TICKET DE CARREGAMENTO ATIVO");
            ItagDescription description7 = new ItagDescription("A FUNÇÃO ACERTO DE CARGA NO SISTEMA FOI HABILITADA");
            ItagDescription description8 = new ItagDescription("CARREGAMENTO FINALIZADO PELO SISTEMA DO CARREGAMENTO");

            ItagConfig itagConfig1 = new ItagConfig("BICA1_ABERTA_MANUAL", description1, 1);
            ItagConfig itagConfig2 = new ItagConfig("BICA2_ABERTA_MANUAL", description2, 1);
            ItagConfig itagConfig3 = new ItagConfig("BICA3_ABERTA_MANUAL", description3, 1);
            ItagConfig itagConfig4 = new ItagConfig("BICA4_ABERTA_MANUAL", description4, 1);
            ItagConfig itagConfig5 = new ItagConfig("CA01_ABERTA_MANUAL", description5, 1);
            ItagConfig itagConfig6 = new ItagConfig("CA02_ABERTA_MANUAL", description6, 1);
            ItagConfig itagConfig7 = new ItagConfig("ACERTO_CARGA", description7, 1);
            ItagConfig itagConfig8 = new ItagConfig("CARREGAMENTO_REPROVADO", description8, 1);
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
            plc.getItagConfigs().add(itagConfig1);
            plc.getItagConfigs().add(itagConfig2);
            plc.getItagConfigs().add(itagConfig3);
            plc.getItagConfigs().add(itagConfig4);
            plc.getItagConfigs().add(itagConfig5);
            plc.getItagConfigs().add(itagConfig6);
            plc.getItagConfigs().add(itagConfig7);
            plc.getItagConfigs().add(itagConfig8);

            itagConfigService.save(itagConfig1);
            itagConfigService.save(itagConfig2);
            itagConfigService.save(itagConfig3);
            itagConfigService.save(itagConfig4);
            itagConfigService.save(itagConfig5);
            itagConfigService.save(itagConfig6);
            itagConfigService.save(itagConfig7);
            itagConfigService.save(itagConfig8);

            plcService.save(plc);
        }

        Plc plc = plcService.getAll().get(0);
        PlcThread plcThread = new PlcThread(plc);
        pool.addPlcThread(plcThread);
        System.out.println(plcThread);
        Thread thread = new Thread(plcThread, plc.getDescription());
        pool.addThread(thread);
        thread.start();
    }
}
