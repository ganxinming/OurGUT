package com.gan.ourspring.hystrix.annotation;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ganxinming
 * @createDate 2021/1/12
 * @description
 */
@Configuration
public class HystrixConfig {

	@Bean
	public HystrixCommandAspect hystrixCommandAspect(){
		return new HystrixCommandAspect();
	}
}
