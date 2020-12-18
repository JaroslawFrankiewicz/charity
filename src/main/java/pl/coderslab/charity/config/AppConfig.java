package pl.coderslab.charity.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import pl.coderslab.charity.controller.TypeConverter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Validator;
import java.util.Locale;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.coderslab")
@EnableJpaRepositories(basePackages = "pl.coderslab.charity.repository")
@EnableTransactionManagement
    public class AppConfig implements WebMvcConfigurer {

        @Bean
        public ViewResolver viewResolver() {
            InternalResourceViewResolver viewResolver =
                    new InternalResourceViewResolver();
            viewResolver.setPrefix("/WEB-INF/views/");
            viewResolver.setSuffix(".jsp");
            return viewResolver;
        }

        @Override
        public void configureDefaultServletHandling(
                DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }

        @Bean
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean
        public LocalEntityManagerFactoryBean entityManagerFactory() {
            LocalEntityManagerFactoryBean entityManagerFactoryBean
                    = new LocalEntityManagerFactoryBean();
            entityManagerFactoryBean.setPersistenceUnitName("charity_donation");
            return entityManagerFactoryBean;
        }

        @Bean
        public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }

        @Bean(name = "localeResolver")
        public LocaleContextResolver getLocaleContextResolver() {
            SessionLocaleResolver localeResolver = new SessionLocaleResolver();
            localeResolver.setDefaultLocale(new Locale("pl", "PL"));
            return localeResolver;
        }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

//    @Bean
//    public TypeConverter getTypeConverter() {
//        return new TypeConverter();
//    }
}

