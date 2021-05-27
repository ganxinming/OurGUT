package com.ourGut.ourSpring.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author ganxinming
 * @createDate 2021/4/30
 * @description
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/**
	 * 允许Cors，可与Vue交互
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.maxAge(3600)
				.allowCredentials(true);
	}
}
