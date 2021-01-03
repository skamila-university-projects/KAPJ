package skamila.kapj.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class SpringInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfiguration.class, SpringConfiguration.class, HibernatePersistenceConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
