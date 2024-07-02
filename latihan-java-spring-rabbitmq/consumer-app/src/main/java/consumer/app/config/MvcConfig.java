package consumer.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfig implements WebMvcConfigurer {

    @Value("${file.image.path}")
    private String fileImagePath;
    @Value("${file.template.path}")
    private String fileTemplatePath;
    @Value("${file.report.path}")
    private String fileReportPath;
    @Value("${file.static.path}")
    private String fileStaticPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(fileImagePath);
        registry.addResourceHandler("/templates/**")
                .addResourceLocations(fileTemplatePath);
        registry.addResourceHandler("/reports/**")
                .addResourceLocations(fileReportPath);
        registry.addResourceHandler("/css/**", "/js/**", "/vendors/**")
                .addResourceLocations(fileStaticPath + "/css/", fileStaticPath + "/js/", fileStaticPath + "/vendors/");
    }
}
