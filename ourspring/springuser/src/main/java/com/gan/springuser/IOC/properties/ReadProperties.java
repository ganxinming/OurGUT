package com.gan.springuser.IOC.properties;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/2/5
 * @description 读取配置文件 通过@PropertySource+@Value读取
 */
@Data
@PropertySource(value = {"classpath:test-a.properties"}, encoding = "UTF-8")
@Component
public class ReadProperties {
	/**
	 * test-a.properties 文件中 test.a 属性
	 */
	@Value("${test.a}")
	private String aValue;
}
