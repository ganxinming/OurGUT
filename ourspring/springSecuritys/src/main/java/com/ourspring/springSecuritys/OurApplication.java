package com.ourspring.springSecuritys;

import com.ourspring.springSecuritys.entity.TestUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganxinming
 * @createDate 2021/1/26
 * @description
 */
@SpringBootApplication
public class OurApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurApplication.class, args);
		TestUser.builder().testUpdate("123").build();
		TestUser testUser=new TestUser("!23");
		testUser.getTestUpdate();
		testUser.setTestUpdate("123");
	}

}

