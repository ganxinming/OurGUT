package com.gan.ourspring.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ganxinming
 * @createDate 2020/12/5
 * @description 开启异步，记得使用@EnableAsync注解
 */
@EnableAsync
@SpringBootApplication
public class MySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class,args);
	}
}
