package com.gan.ourspring.async;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ganxinming
 * @createDate 2020/12/5
 * @description 开启异步，记得使用@EnableAsync注解
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class MySpringBootApplication {

//	static Logger logger = LoggerFactory.getLogger("dubbo_consumer_filter_log");
	public static void main(String[] args) {
		log.info("!@3");
		SpringApplication.run(MySpringBootApplication.class,args);
	}
}
