package IntegraLogger.Application;

import IntegraLogger.Controller.Service.PlcService;
import IntegraLogger.Model.Plc.Plc;
import IntegraLogger.Model.Tag.ItagConfig;
import etherip.protocol.RegisterSession;
import etherip.protocol.TcpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlcConnection {

    @Autowired
    private PlcService plcService;

    public void createConnecion(){
        List<Plc> plcs = plcService.getAll();

        for (Plc plc : plcs){
            plcConnect(plc);
        }


        System.out.println(plcs.toString());
    }

    private synchronized boolean plcConnect(Plc plc) {
        try {
            TcpConnection con = new TcpConnection(plc.getIp(), plc.getSlot());
            RegisterSession register = new RegisterSession();
            con.execute(register);
            con.setSession(register.getSession());
//            plc.setConnection(con);
            System.out.println(plc.getDescription() + " connection is open: " + con.isOpen());
            plcService.getConnections().add(con);
            return true;
        } catch (Exception e) {
            System.out.println("Controller " + plc.getDescription() + " - IP: " + plc.getIp() + " not connected");
            e.printStackTrace();
            return false;
        }
    }

}
