package sepe;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.ErrorPageFilter;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import sepe.handlers.EmailValidator;
import sepe.handlers.PasswordMatchesValidator;
import sepe.handlers.UserValidator;
import weblogic.ejbgen.JndiName;

import javax.naming.*;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static java.security.AccessController.getContext;


@EnableAutoConfiguration
@Configuration
@ComponentScan({"sepe.config", "sepe.controller", "sepe.dto.converter", "sepe.handlers", "sepe.service", "sepe.webservicessepe"})
@EnableTransactionManagement
@EnableJpaRepositories("sepe.repository")
//@EntityScan("sepe.domain")
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {


    private static Class<Application> applicationClass = Application.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    public static void main(final String[] args) {
        final ApplicationContext ctx = SpringApplication.run(applicationClass, args);
        RepositoryRestConfiguration restConfiguration = ctx.getBean(RepositoryRestConfiguration.class);
        restConfiguration.setReturnBodyOnCreate(true);

        Logger logger = LoggerFactory.getLogger("Application");
        logger.debug("Let's inspect the beans provided by Spring Boot:");

        final String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (final String beanName : beanNames) {
            logger.debug(beanName);
        }
;
    }

    @Bean
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }

    @Bean
    public PasswordMatchesValidator passwordMatchesValidator() {
        return new PasswordMatchesValidator();
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }


    @Bean
    protected JpaVendorAdapter createJpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        //HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.ORACLE);
        adapter.setGenerateDdl(false);
        adapter.setShowSql(true);

        return adapter;
    }

    @Bean(destroyMethod = "")
    public DataSource dataSource() throws Exception {

        // Application Server Datasource
        Context initContext = new InitialContext();
        return (DataSource) initContext.lookup("ds/SP_PTL");
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Throwable {
        LocalContainerEntityManagerFactoryBean factoryBean
                = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[]{"sepe.domain", "sepe.config", "sepe.controller", "sepe.dto.converter", "sepe.handlers", "sepe.service"});
        //factoryBean.setLoadTimeWeaver(loadTimeWeaver());
        factoryBean.setPersistenceUnitName("sepe");
        factoryBean.setBeanName("entityManagerFactory");
       // factoryBean.setJpaDialect(new HibernateJpaDialect());
        factoryBean.setJpaVendorAdapter(createJpaVendorAdapter());
        factoryBean.getJpaPropertyMap().put("eclipselink.cache.shared.default","false");
        //factoryBean.getJpaPropertyMap().put("eclipselink.cache.type.default","");
        /*factoryBean.getJpaPropertyMap().put("eclipselink.weaving.changetracking", "true");
        factoryBean.getJpaPropertyMap().put("eclipselink.weaving.lazy","true");
        factoryBean.getJpaPropertyMap().put("eclipselink.weaving.eager","true");
        factoryBean.getJpaPropertyMap().put("eclipselink.weaving.fetchgroups","true");
        factoryBean.getJpaPropertyMap().put("eclipselink.weaving.internal","true");*/
        factoryBean.getJpaPropertyMap().put("eclipselink.weaving","false");

        /*factoryBean.getJpaPropertyMap().put("eclipselink.connection-pool.default.initial","20");
        factoryBean.getJpaPropertyMap().put("eclipselink.connection-pool.default.min","20");
        factoryBean.getJpaPropertyMap().put("eclipselink.connection-pool.default.max","120");*/

        //mapping.setIsCascadeOnDeleteSetOnDatabase(true)

        //factoryBean.getJpaPropertyMap().put("eclipselink.id-validation","Null");
       // factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.afterPropertiesSet();
        return factoryBean ;
    }

    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver() throws Throwable {
        InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        return loadTimeWeaver;
    }

    @Autowired
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
