package com.gan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author ganxinming
 * @createDate 2020/10/18
 * @description
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})//排除数据库配置，避免启动报错
public class WebStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebStartApplication.class,args);
	}
}