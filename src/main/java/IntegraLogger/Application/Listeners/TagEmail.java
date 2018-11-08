package IntegraLogger.Application.Listeners;

import IntegraLogger.Controller.Service.BeanUtil;
import IntegraLogger.Controller.Service.ItagValueService;
import etherip.Tag;
import etherip.TagListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class TagEmail implements TagListener {

    private ItagValueService itagValueService = BeanUtil.getBean(ItagValueService.class);

    private static final Logger logger = LoggerFactory.getLogger(TagEmail.class);

    public TagEmail() {
    }

    @Override
    public synchronized void tagUpdate(Tag tag) {

        logger.info("Email Sender -> Value: " + tag.getData());
//        ItagValue value = new ItagValue(tag.getName(),tag.getData().getType(),new Date(),tag.getData().getNumber(0).floatValue());
//        plcService.test();
    }

    @Override
    public synchronized void tagError(Tag tag) {
        System.out.println("error Email");
    }
}
