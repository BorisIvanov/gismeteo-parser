package gismeteo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = {"gismeteo"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
/*
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine result = new SpringTemplateEngine();
        result.setTemplateResolver(viewResolver());
        return result;
    }*/

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCache(false);
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        //templateEngine.setMessageSource(messageSource);
        /*
        templateEngine.setAdditionalDialects(
                Collections.singletonList(new SpringSecurityDialect())
                        .stream()
                        .collect(Collectors.toSet()));*/
        return templateEngine;
    }

    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
/*
    @Bean
    public JacksonAnnotationIntrospector jacksonAnnIntrospector() {
        return new JacksonAnnotationIntrospector();
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        ObjectMapper result = new ObjectMapper();
        result.setAnnotationIntrospector(jacksonAnnIntrospector());
        result.getSerializationConfig().withInsertedAnnotationIntrospector(jacksonAnnIntrospector());
        result.getDeserializationConfig().withInsertedAnnotationIntrospector(jacksonAnnIntrospector());
        //result.registerModule(new JavaTimeModule());
        return result;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter json = new MappingJackson2HttpMessageConverter();
        json.setObjectMapper(jacksonObjectMapper());
        converters.add(json);
    }*/

}