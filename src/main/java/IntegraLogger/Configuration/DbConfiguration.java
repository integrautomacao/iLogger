package IntegraLogger.Configuration;

import IntegraLogger.Application.AppValues;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "IntegraLogger.*")
//public class DbConfiguration {
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(AppValues.getProperty("dataSource.className"));
//        dataSource.setUrl(AppValues.getProperty("dataSource.url"));
//        dataSource.setUsername(AppValues.getProperty("dataSource.user"));
//        dataSource.setPassword(AppValues.getProperty("dataSource.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//        vendorAdapter.setShowSql(false);
//        vendorAdapter.setDatabasePlatform(AppValues.getProperty("hibernate.dialect"));
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setJpaProperties(getHibernateProperties());
//        factory.setPackagesToScan("IntegraLogger");
//        factory.setDataSource(dataSource());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setDataSource(dataSource());
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
//
//    Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.ddl-auto", "update");
//        properties.setProperty("hibernate.dialect", AppValues.getProperty("hibernate.dialect"));
//
//        properties.setProperty("hibernate.show_sql", "true");
//
//        return properties;
//    }
//}

@Configuration
//@EnableTransactionManagement
public class DbConfiguration {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(AppValues.getProperty("dataSource.className"))
                .url(AppValues.getProperty("dataSource.url"))
                .username(AppValues.getProperty("dataSource.user"))
                .password(AppValues.getProperty("dataSource.password"))
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan("IntegraLogger");
        emf.setJpaProperties(additionalProperties());
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", AppValues.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }
}
