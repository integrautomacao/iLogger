package IntegraLogger.Controller.Service.EmailServices;

import IntegraLogger.Application.AppValues;
import IntegraLogger.Controller.Service.BeanUtil;
import IntegraLogger.Controller.Service.EmailService;
import IntegraLogger.Controller.Service.FreemakerUtils;
import IntegraLogger.Controller.Service.ItagConfigService;
import IntegraLogger.Model.Tag.ItagValue;
import freemarker.template.TemplateException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class EmailSenderItag implements Runnable {
    private ItagConfigService configService = BeanUtil.getBean(ItagConfigService.class);



    HtmlEmail email = new HtmlEmail();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");
    private ItagValue value;
    private String message = null;
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderItag.class);


    public EmailSenderItag(ItagValue value) {
        this.value = value;
    }

    public synchronized void sendMail() {
        Map map = new HashMap();
        System.out.println(value.getLastUpdate());
        map.put("tag", value.getName());

        map.put("timeStamp", sdf.format(value.getLastUpdate()));
        if (value.getValueBool()) {
            map.put("value", "Condição de alarme ATIVA");
        } else {
            map.put("value", "Condição de alarme INATIVA");
        }

        map.put("desc", configService.getByName(value.getName()).getDescription().getValue());

        try {
            message = FreemakerUtils.parseTemplate(map, "email.ftl");
            System.out.println(email);
        } catch (TemplateException e) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Não foi possível acessar o arquivo, erro de template");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Não foi possível acessar o arquivo, erro de IO");
            e.printStackTrace();
        }
    }

    private void send() throws EmailException {
        String[] mailToArray = EmailService.getInstance().getRecipientList();
        HtmlEmail email = EmailService.getInstance().getHtmlEmail(this.message, mailToArray);
        email.send();
        logger.info("email enviado para: " + emailsTo(mailToArray) + " - evento: '" + value.getName() + "' valor= " + value.getValueBool().booleanValue());
    }

    private String emailsTo(String[] emails) {
        String toReturn = "dest ";
        for (String email : emails) {
            toReturn = toReturn + "\n" + " -> " + email;
        }
        return toReturn;
    }

    @Override
    public void run() {
        sendMail();
        try {
            send();
        } catch (EmailException e) {
            System.out.println("Could not send email");
            e.printStackTrace();
        }
//TODO create a usefull message to success sent email

    }
}
