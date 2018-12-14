package IntegraLogger.Controller.Service;

import IntegraLogger.Controller.Repository.PlcRepository;
import IntegraLogger.Model.Plc.Plc;
import etherip.protocol.TcpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlcService extends ServiceBase<Plc, Long, PlcRepository> {

    @Autowired
    public PlcService(PlcRepository repository) {
        super(repository);
    }

    private List<TcpConnection> connections = new ArrayList<>();

    public List<TcpConnection> getConnections() {
        return connections;
    }

    public void test() {
        System.out.println("works!");
    }

    public int countInt() {
        return (int)repository.count();
    }

    public Plc getByDescription(String name) {
        return repository.getByDescription(name);
    }
}
