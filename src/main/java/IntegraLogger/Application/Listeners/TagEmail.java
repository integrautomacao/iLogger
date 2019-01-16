package IntegraLogger.Application.Listeners;

import IntegraLogger.Controller.Service.BeanUtil;
import IntegraLogger.Controller.Service.EmailServices.EmailSenderItag;
import IntegraLogger.Controller.Service.ItagValueService;
import IntegraLogger.Model.Tag.ItagValue;
import etherip.Tag;
import etherip.TagListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class TagEmail implements TagListener {

    private ItagValueService itagValueService = BeanUtil.getBean(ItagValueService.class);

    private static final Logger logger = LoggerFactory.getLogger(TagEmail.class);

    public TagEmail() {
    }

    @Override
    public synchronized void tagUpdate(Tag tag) {

//        logger.info("Email Sender -> Value: " + tag.getData());
        ItagValue value = null;
        try {
            value = itagValueService.tagToItag(tag);
        } catch (Exception e) {
            logger.info("was not possible parse the tag object in itagValue object");
            e.printStackTrace();
            return;
        }

        if (itagValueService.checkBooleanValue(value)) {
            EmailSenderItag emailSenderItag = null;

            emailSenderItag = new EmailSenderItag(value);

            Thread thread = new Thread(emailSenderItag, "EmailSenderItag -> " + value.getName());
            thread.start();
        }
    }

    @Override
    public synchronized void tagError(Tag tag) {
        System.out.println("error Email");
    }
}
