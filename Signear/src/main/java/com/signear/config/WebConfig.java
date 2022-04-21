package com.signear.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// cors 설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:8086", "http://localhost:3001", "http://localhost:3001",
						"http://api.signear.com:8086", "http://api.signear.com:3001")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "FETCH").allowCredentials(true)
				.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Access-Control-Request-Method",
						"Access-Control-Request-Headers", "Authorization")
				.exposedHeaders("at-jwt-access-token", "at-jwt-refresh-token", "at-jwt-access-token-expire-date",
						"at-jwt-refresh-token-expire-date")
				.maxAge(30000);
	}

}
