package IntegraLogger.Application.Listeners;

import IntegraLogger.Controller.Service.BeanUtil;
import IntegraLogger.Controller.Service.ItagValueService;
import IntegraLogger.Controller.Service.ReportService;
import IntegraLogger.Model.Tag.ItagValue;
import etherip.Tag;
import etherip.TagListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagTriggerReport implements TagListener {
    private ReportService reportService = BeanUtil.getBean(ReportService.class);
    private ItagValueService itagValueService = BeanUtil.getBean(ItagValueService.class);
    private static final Logger logger = LoggerFactory.getLogger(TagTriggerReport.class);

    @Override
    public void tagUpdate(Tag tag) {

        ItagValue value = null;
        try {
            value = itagValueService.tagToItag(tag);
        } catch (Exception e) {
            logger.info("was not possible parse the tag object in itagValue object | TriggerReport");
            e.printStackTrace();
            return;
        }
        if (itagValueService.checkBooleanValue(value)) {
            logger.info("Report Triggered -> " + value.getName());
            reportService.createReport();
        }
    }

    @Override
    public void tagError(Tag tag) {

    }
}
