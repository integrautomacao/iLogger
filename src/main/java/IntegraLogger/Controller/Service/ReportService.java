package IntegraLogger.Controller.Service;

import IntegraLogger.Application.DataTypes;
import IntegraLogger.Model.Misc.IReport;
import IntegraLogger.Model.Tag.ItagValue;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReportService {

    private ItagValueService itagValueService = BeanUtil.getBean(ItagValueService.class);

    public IReport createReport() {
        IReport report = new IReport(new Date());
        report.setEvents(itagValueService.getAllByDateAndType(new Date(), DataTypes.BOOL.toString()));
        //TODO temporary fields for ADM - LEM
        ItagValue value1 = itagValueService.getTopByLastUpdate("CTU_CAMINHOES_CARREGADOS.ACC");
        ItagValue value2 = itagValueService.getTopByLastUpdate("CTU_CAMINHOES_CARREGADOS_C.ACC");

        report.getFields().add(value1.toString());
        report.getFields().add(value2.toString());
        return report;
    }
}
