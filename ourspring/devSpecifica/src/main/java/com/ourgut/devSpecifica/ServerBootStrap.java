package com.ourgut.devSpecifica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganxinming
 * @createDate 2021/8/20
 * @description Bootstrap，Starter
 * 一般作为程序启动器使用，或者作为启动器的基类。通俗来说，可以认为是main函数的入口。
 */
@SpringBootApplication
public class ServerBootStrap {
	public static void main(String[] args) {
		SpringApplication.run(ServerBootStrap.class,args);
	}
}
