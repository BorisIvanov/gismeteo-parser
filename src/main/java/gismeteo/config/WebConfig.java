package gismeteo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();/*
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");*/
        return viewResolver;
    }

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
    }

}