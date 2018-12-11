package IntegraLogger.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppValues {
    static final Logger log = LoggerFactory.getLogger(AppValues.class);
    private static Properties proper = getCustomFileProperties();


    public static String getProperty(String property) {
        return proper.getProperty(property);
    }

    private static Properties getCustomFileProperties() {
        Properties toReturn = new Properties();
        try {
            InputStream input = new FileInputStream("C:/iFiles/" + getCustomPropertiesFileName());
            toReturn.load(input);

        } catch (IOException e) {
            log.info("Custom Properties not found");
            System.out.println("Erro ao obter arquivo de propriedades");
        }
        return toReturn;
    }

    /**
     * @return arquivo de configuração da applicação
     */
    public static String getCustomPropertiesFileName() {
        return "integra.properties";
    }
}
