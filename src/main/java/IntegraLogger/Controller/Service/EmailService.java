package IntegraLogger.Controller.Service;

import IntegraLogger.Application.AppValues;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class EmailService {

    private static EmailService ourInstance = new EmailService();

    public static EmailService getInstance(){
        return ourInstance;
    }

    public String[] getRecipientList() {
        String mailTo = AppValues.getProperty("mailTo");
        String[] mailToArray = mailTo.split(",");
        return mailToArray;
    }

    public HtmlEmail getHtmlEmail(String message, String... recipientArray) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(AppValues.getProperty("hostname"));
        email.setFrom(AppValues.getProperty("from")); // remetente
        email.setAuthentication(AppValues.getProperty("user"), AppValues.getProperty("pass"));
        email.setSmtpPort(Integer.parseInt(AppValues.getProperty("port")));
        email.addTo(recipientArray);

        email.setSubject(AppValues.getProperty("subject"));
        email.setHtmlMsg(message);
        email.setCharset("UTF-8");

        return email;
    }



}
