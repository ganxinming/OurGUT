package com.ourspring.springSecuritys.config;

import com.ourspring.springSecuritys.handler.CheckJwtDeTokenHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ganxinming
 * @createDate 2021/8/8
 * @description
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired CheckJwtDeTokenHandler checkJwtDeTokenHandler;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//只拦截解密token路径
		registry.addInterceptor(checkJwtDeTokenHandler).addPathPatterns("/loginDeToken");
	}
}
