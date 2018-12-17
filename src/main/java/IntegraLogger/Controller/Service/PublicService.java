package IntegraLogger.Controller.Service;

import IntegraLogger.Application.ThreadPool;
import IntegraLogger.DTO.PlcStatusDTO;
import IntegraLogger.Model.Plc.Plc;
import etherip.EtherNetIP;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PublicService {
    private PlcService plcService = BeanUtil.getBean(PlcService.class);

    public PublicService() {
    }

    public List<PlcStatusDTO> getConnectionStatus() {
        List<PlcStatusDTO> statusList = new ArrayList<>();


        for (Thread thread : ThreadPool.getThreads()) {
            PlcStatusDTO statusDTO = new PlcStatusDTO();
            Plc plc = plcService.getByDescription(thread.getName());
            if (plc != null) {
                statusDTO.setDeviceName(plc.getDescription());
                statusDTO.setStatusDesc(testConnectTcp(plc));
            }
            if (statusDTO.getStatusDesc() != null) {
                statusDTO.setStatus(true);
            } else {
                statusDTO.setStatus(false);
                statusDTO.setStatusDesc("Not Connected!");
            }
            statusList.add(statusDTO);
        }
        return statusList;
    }


    public String testConnectTcp(Plc plc) {
        EtherNetIP controller = new EtherNetIP(plc.getIp(), plc.getSlot());
        String identity;

        try {
            controller.connectTcp();

            System.out.println("\n* Plc: " + plc.getDescription() + " - IP: " + plc.getIp() + " \n* Connected:\n*\n");
            identity = controller.getIdentity().toString();
            controller.close();
            return identity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
