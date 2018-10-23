package com.adicse.facturador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		System.out.println("Bean Configuracion ...........................");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // you USUALLY want this
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedHeader("Access-Control-Allow-Headers: X-SomeHeader");

		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

}
