package com.gan.springuser.IOC.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/2/5
 * @description yml 读取方式 通过@ConfigurationProperties读取
 */
@ConfigurationProperties(prefix = "bob")
@Component
@Data
public class YmlDemo {

	/**
	 * 对应 配置文件 bob.id
	 */
	private int id;

	/**
	 * 对应配置文件 bob.name
	 */
	private String name;
}

