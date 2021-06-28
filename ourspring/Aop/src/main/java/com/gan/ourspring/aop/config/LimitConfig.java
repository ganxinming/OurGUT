package com.gan.ourspring.aop.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/6/18
 * @description
 */
@Data
//@Component
//@ConfigurationProperties(prefix = "ratelimit")
//@PropertySource(value = "classpath:ratelimit.properties")
public class LimitConfig {
	//最大限制
	private Double limitMax;
	//阻塞超时时间
	private Long waitTime;
	//限流开关
	private boolean limitSwitch;
}
