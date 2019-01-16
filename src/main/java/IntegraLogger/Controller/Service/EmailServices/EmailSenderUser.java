package IntegraLogger.Controller.Service.EmailServices;

import IntegraLogger.Controller.Service.EmailService;
import IntegraLogger.Controller.Service.FreemakerUtils;
import IntegraLogger.Model.User.Usuario;
import freemarker.template.TemplateException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmailSenderUser implements Runnable {


    HtmlEmail email = new HtmlEmail();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");
    private Usuario usuario;
    private String message;
    private String template;

    public EmailSenderUser(Usuario usuario, boolean newUser) {
        this.usuario = usuario;
        if (newUser) {
            this.template = "userEmail.ftl";
        } else {
            this.template = "userPassReset.ftl";
        }
    }

    private void buildEmail() {
        Map map = new HashMap();
        map.put("nome", usuario.getName());
        map.put("email", usuario.getEmail());
        map.put("senha", usuario.getPassword());
        map.put("grupo", usuario.getSector().getName());
        map.put("dataHora", sdf.format(new Date()));

        try {
            message = FreemakerUtils.parseTemplate(map, this.template);
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

    private void sendEmail() throws EmailException {

        HtmlEmail email = EmailService.getInstance().getHtmlEmail(this.message, usuario.getEmail());
        email.send();

    }

    @Override
    public void run() {
        buildEmail();
        try {
            sendEmail();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
