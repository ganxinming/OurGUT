package com.ourspring.sendHttpRequest.config;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author ganxinming
 * @createDate 2021/1/18
 * @description
 */

@Configuration
public class RestTemplateConfig {

//	@Bean
//	public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory){
//		return new RestTemplate(clientHttpRequestFactory);
//	}

//	@Bean
//	public ClientHttpRequestFactory clientHttpRequestFactory(){
//		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//		factory.setConnectTimeout(15000);
//		factory.setReadTimeout(5000);
//		return factory;
//	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {

		return builder
				.requestFactory(HttpComponentsClientHttpRequestFactory.class)
				.setConnectTimeout(Duration.ofMillis(1000))
				.setReadTimeout(Duration.ofMillis(1000))
				.build();
	}
}
