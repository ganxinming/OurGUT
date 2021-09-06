package com.ourgut.esjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ganxinming
 * @createDate 2021/9/3
 * @description
 */
@SpringBootApplication
@EnableScheduling // 开启定时任务功能
public class AppBootStrap {
	public static void main(String[] args) {
		SpringApplication.run(AppBootStrap.class,args);
	}
}
