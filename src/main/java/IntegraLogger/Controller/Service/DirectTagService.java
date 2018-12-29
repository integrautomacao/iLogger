package IntegraLogger.Controller.Service;

import IntegraLogger.DTO.CIPDataDTO;
import IntegraLogger.DTO.MultipleTagReadDTO;
import IntegraLogger.Model.Plc.Plc;
import etherip.EtherNetIP;
import etherip.types.CIPData;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DirectTagService {
    SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm:ss");


    public CIPDataDTO getDirectValue(Plc plc, String tag) {
        try (EtherNetIP con = new EtherNetIP(plc.getIp(), plc.getSlot())) {
            con.connectTcp();
            CIPData data = con.readTag(tag);
            CIPDataDTO value = CIPDataDTOFromCIP(data);
            value.setName(tag);
            Date date = new Date();
            value.setDate(sdfDate.format(date));
            value.setHour(sdfHour.format(date));
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CIPDataDTO CIPDataDTOFromCIP(CIPData data) throws Exception {
        CIPDataDTO value = new CIPDataDTO();

        if (data.isNumeric()) {
            switch (data.getType().name()) {
                case "BOOL":
                    if (data.getNumber(0).intValue() == 0) {
                        value.setValue("0");
                    } else {
                        value.setValue("1");
                    }
                    break;
                case "REAL":
                    value.setValue(String.valueOf(data.getNumber(0).floatValue()));
                    break;
                default:
                    value.setValue(String.valueOf(data.getNumber(0).intValue()));
                    break;
            }
        } else {
            value.setValue(data.getString());
        }
        return value;
    }

    public List<CIPDataDTO> getValuesFromArray(MultipleTagReadDTO data) {
        try (EtherNetIP con = new EtherNetIP(data.getPlc().getIp(), data.getPlc().getSlot())) {
            con.connectTcp();

            List<CIPDataDTO> cipDataDTOS = new ArrayList<>();
            Date date = new Date();

            for (String tagName : data.getTags()) {
                CIPDataDTO dataDTO;
                try {
                    CIPData value = con.readTag(tagName);
                    dataDTO = CIPDataDTOFromCIP(value);
                    dataDTO.setName(tagName);
                    dataDTO.setHour(sdfHour.format(date));
                    dataDTO.setDate(sdfDate.format(date));
                    cipDataDTOS.add(dataDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                    dataDTO = new CIPDataDTO();
                    dataDTO.setName(tagName);
                    dataDTO.setValue("Erro de Leitura");
                    cipDataDTOS.add(dataDTO);
                }
            }
            return cipDataDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
