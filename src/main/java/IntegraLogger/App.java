package IntegraLogger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"IntegraLogger.Controller",
//        "IntegraLogger.Enviroment",
//        "IntegraLogger.Model",
//        "IntegraLogger.Application",
//        "IntegraLogger.Configuration", })
//@SpringBootApplication
//public class App {
//    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
//    }
//}


@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

}
