package com.ourspring.springdubboconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author ganxinming
 * @createDate 2021/3/17
 * @description
 */

@SpringBootApplication
@ImportResource(locations= {"classpath:consumer.xml"})
public class SpringBootAppConsumer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppConsumer.class,args);
	}
}
