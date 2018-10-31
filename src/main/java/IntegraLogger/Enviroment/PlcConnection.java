package IntegraLogger.Enviroment;

import IntegraLogger.Application.Listeners.ListenersIndex;
import IntegraLogger.Controller.Service.PlcService;
import IntegraLogger.Model.Plc.Plc;
import IntegraLogger.Model.Tag.ItagConfig;
import IntegraLogger.Model.User.User;
import etherip.protocol.Connection;
import etherip.protocol.RegisterSession;
import etherip.protocol.TcpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlcConnection {

//    @Autowired
//    private PlcService plcService;

    public Connection plcConnect(Plc plc) {
        try {
            TcpConnection con = new TcpConnection(plc.getIp(), plc.getSlot());
            RegisterSession register = new RegisterSession();
            con.execute(register);
            con.setSession(register.getSession());
            System.out.println(plc.getDescription() + " connection is open: " + con.isOpen());
//            plcService.getConnections().add(con);
            return con;
        } catch (Exception e) {
            System.out.println("Controller " + plc.getDescription() + " - IP: " + plc.getIp() + " not connected");
            e.printStackTrace();
            return null;
        }
    }
}
