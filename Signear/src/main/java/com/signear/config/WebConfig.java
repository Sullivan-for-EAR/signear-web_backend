package com.signear.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://*:8086", "http://localhost:8086")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "FETCH").allowCredentials(true)
				.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Access-Control-Request-Method",
						"Access-Control-Request-Headers", "Authorization")
				.maxAge(30000);
	}

}
