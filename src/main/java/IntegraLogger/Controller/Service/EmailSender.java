package IntegraLogger.Controller.Service;

import IntegraLogger.Model.Tag.ItagValue;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class EmailSender implements Runnable {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");
    private ItagValue value;

    public EmailSender(ItagValue value) {
        this.value = value;
    }

    public synchronized void sendMail() {
        Map map = new HashMap();
        String email = null;
        System.out.println(value.getLastUpdate());
        map.put("tag", value.getName());
        map.put("desc", "descriçããão");
        map.put("timeStamp", sdf.format(value.getLastUpdate()));
        if (value.getValueBool()) {

            map.put("value", "Condição de alarme ATIVA");
        } else {
            map.put("value", "Condição de alarme INATIVA");

        }

        try {
            email = FreemakerUtils.parseTemplate(map, "email.ftl");
            System.out.println(email);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        sendMail();

        System.out.println("fim da thread");
    }
}
