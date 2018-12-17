package IntegraLogger.Application.Listeners;

import IntegraLogger.Controller.Service.BeanUtil;
import IntegraLogger.Controller.Service.ItagValueService;
import IntegraLogger.Enviroment.ApplicationStartup;
import IntegraLogger.Model.Tag.ItagValue;
import etherip.Tag;
import etherip.TagListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TagPersist implements TagListener {
    private static final Logger logger = LoggerFactory.getLogger(TagPersist.class);

    private ItagValueService itagValueService = BeanUtil.getBean(ItagValueService.class);

    @Override
    public synchronized void tagUpdate(Tag tag) {

        ItagValue value = null;
         try {
             value = itagValueService.tagToItag(tag);
        } catch (Exception e) {
            e.printStackTrace();
        }

        itagValueService.checkAndSave(value);
    }



    @Override
    public synchronized void tagError(Tag tag) {
        System.out.println("error Persist");
    }
}
