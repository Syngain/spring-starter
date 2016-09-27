package com.hualongdata.springstarter.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;
import com.hualongdata.util.poi.ExcelHelper;
import com.hualongdata.springstarter.web.utils.HlTokenAuthInterceptor;
import com.hualongdata.springstarter.web.utils.DefaultHttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring Boot Custom Configuration
 * Created by yangbajing on 16-8-24.
 */
@Configuration
public class CustomConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private SwaggerSetting swaggerSetting;

    @Bean
    public Module jacksonModuleScala() {
        return new DefaultScalaModule();
    }

    @Bean
    public ExcelHelper excelHelper() {
        return new ExcelHelper();
    }

    @Bean
    public HlTokenAuthInterceptor hlTokenAuthInterceptor() {
        return new HlTokenAuthInterceptor();
    }

    @Bean
    public DefaultHttpInterceptor defaultHttpInterceptor() {
        return new DefaultHttpInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(defaultHttpInterceptor())
                .addPathPatterns("/inapi/**", "/sign/**");
        registry.addInterceptor(hlTokenAuthInterceptor())
                .addPathPatterns("/inapi/**");
    }

}
