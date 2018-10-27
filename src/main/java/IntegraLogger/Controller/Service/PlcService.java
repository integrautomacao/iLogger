package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.PlcRepository;
import IntegraLogger.Model.Plc.Plc;
import etherip.protocol.TcpConnection;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class PlcService extends ServiceBase<Plc, Long, PlcRepository> {

    public PlcService(PlcRepository repository) {
        super(repository);
    }


    private List<TcpConnection> connections = new ArrayList<>();

    public List<TcpConnection> getConnections() {
        return connections;
    }

    @Scheduled(fixedRate = 5000)
    private void checkConnection() {
        for (TcpConnection connection : connections) {

            System.out.println(connection.getSession());
            System.out.println(connection.getSlot());
            try {
                System.out.println(connection.isOpen());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("-------------------------");
        }
    }
}
