package IntegraLogger.Controller.Service;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

public class FreemakerUtils {
    private static Configuration cfg = new Configuration();

    public synchronized static final String parseTemplate(Map map, String templateName) throws TemplateException, IOException {
        cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.home") + "/iFiles/Templates"));
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        Template t = cfg.getTemplate(templateName);
        StringWriter writer = new StringWriter();
        cfg.setDefaultEncoding("UTF-8");
        cfg.setEncoding(new Locale("PT-BR"), "UTF-8");
        t.process(map, writer);
        writer.flush();
        writer.close();

        return writer.toString();
    }
}
