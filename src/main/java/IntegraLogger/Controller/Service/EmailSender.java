package IntegraLogger.Controller.Service;

import IntegraLogger.Application.AppValues;
import IntegraLogger.Model.Tag.ItagValue;
import freemarker.template.TemplateException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class EmailSender implements Runnable {
    private ItagConfigService configService = BeanUtil.getBean(ItagConfigService.class);

    HtmlEmail email = new HtmlEmail();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");
    private ItagValue value;
    private String message = null;
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);


    public EmailSender(ItagValue value) {
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

    private void send() {
        try {
            String mailTo = AppValues.getProperty("mailTo");
            String[] mailToArray = mailTo.split(",");


            this.email.setHostName(AppValues.getProperty("hostname"));
            this.email.setFrom(AppValues.getProperty("from")); // remetente
            this.email.setAuthentication(AppValues.getProperty("user"), "pass");
            this.email.setSmtpPort(Integer.parseInt(AppValues.getProperty("port")));
            this.email.setSSLCheckServerIdentity(Boolean.valueOf(AppValues.getProperty("ssl")));
            this.email.addTo(mailToArray);

            this.email.setSubject("Alarme de teste");
            this.email.setHtmlMsg(message);
            this.email.setCharset("UTF-8");
            email.send();
            logger.info("email enviado para: "+mailToArray+" - evento: '" + value.getName() + "' valor= " + value.getValueBool().booleanValue());
        } catch (
                EmailException e) {
            System.out.println("Could not send email");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        sendMail();
        send();
//TODO create a usefull message to success sent email

    }
}
