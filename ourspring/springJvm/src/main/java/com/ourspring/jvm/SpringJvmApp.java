package com.ourspring.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganxinming
 * @createDate 2021/6/15
 * @description
 *
 *
 * 运行前：指定JVM参数
 * -Xms6m -Xmx6m -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC  -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m
 */
@SpringBootApplication
public class SpringJvmApp {
	public static void main(String[] args) {
		SpringApplication.run(SpringJvmApp.class,args);
	}
}
