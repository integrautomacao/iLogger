package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.PlcRepository;
import IntegraLogger.Model.Plc.Plc;
import etherip.protocol.TcpConnection;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlcService extends ServiceBase<Plc, Long, PlcRepository> {

    public PlcService(PlcRepository repository) {
        super(repository);
    }

    private List<TcpConnection> connections = new ArrayList<>();

    public List<TcpConnection> getConnections() {
        return connections;
    }
}
