package IntegraLogger.Controller.Service;

import IntegraLogger.Model.Tag.ItagConfig;
import IntegraLogger.Model.Tag.ItagValue;
import freemarker.template.TemplateException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send() {
        try {

            this.email.setHostName("mail.integrautomacao.com.br");
            this.email.setFrom("engenharia@integra.com"); // remetente
            this.email.setAuthentication("engenharia@integrautomacao.com.br", "_Engenharia1");
            this.email.setSmtpPort(587);
            this.email.setSSL(false);
            this.email.setTLS(false);
            this.email.addTo("wdouglascosta@outlook.com");
            this.email.addTo("rafael@integrautomacao.com.br");
            this.email.addTo("joldmar@integrautomacao.com.br");
            this.email.setSubject("Alarme de teste");
            this.email.setHtmlMsg(message);
            this.email.setCharset("UTF-8");
            email.send();
        } catch (
                EmailException e) {
            System.out.println("Could not build email");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        sendMail();
        send();

        System.out.println("fim da thread");
    }
}
