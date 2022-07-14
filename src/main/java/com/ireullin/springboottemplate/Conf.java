package com.ireullin.springboottemplate;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ireullin.springboottemplate.components.MyHandler;

/*
 * 自定義的設定
 */
@Configuration
public class Conf implements WebMvcConfigurer {

    /*
     * 除了login其他都要先經過MyHandler 
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandler())
            .addPathPatterns("/**")
            .excludePathPatterns("/login")
            .excludePathPatterns("/verify")
            .excludePathPatterns("/info")
            .excludePathPatterns("/assets/**");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       
    }
    
}
