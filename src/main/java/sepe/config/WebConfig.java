package sepe.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Nonnull;
import javax.servlet.MultipartConfigElement;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Needed to support file uploads.
     */
    @Bean
    @Nonnull
    MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
            container.addErrorPages(error401Page, error403Page, error404Page, error500Page);
        });
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/login").setViewName(Constants.LOGIN_TEMPLATE);
        registry.addViewController("/register").setViewName(Constants.REGISTER_TEMPLATE);
        registry.addViewController("/ikaLogin").setViewName("ikaLogin");
        registry.addViewController("/citizenProfile").setViewName(Constants.CITIZEN_PROFILE_TEMPLATE);
        registry.addViewController("/employeeProfile").setViewName(Constants.EMPLOYEE_PROFILE_TEMPLATE);
    }

}
