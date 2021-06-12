package com.ourspring.data;

import cn.hutool.core.lang.Snowflake;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganxinming
 * @createDate 2021/6/10
 * @description
 */
@SpringBootApplication
@MapperScan("com.ourspring.data.mybatisPlus.mapper")
public class SpringBootStart {

//	@Autowired
//	private Snowflake snowflake;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStart.class,args);
	}
}
