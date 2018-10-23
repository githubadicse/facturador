package com.adicse.facturador.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration 
public class CoreConfig {
	@Bean
	MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setMaxFileSize("5120MB");
	    factory.setMaxRequestSize("5120MB");
	    return factory.createMultipartConfig();
	}
}
