package com.gan.ourspring.filter;


import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */

/**
 * 代码有点问题，无法启动
 */
@Slf4j
@SpringBootApplication
public class OurspringApplication {

	public static void main(String[] args) {
		for (int i = 0; i <10 ; i++) {
			log.info("132");
			log.error("132");
			log.warn("132");
		}

		SpringApplication.run(OurspringApplication.class, args);
	}

}
