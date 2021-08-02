package com.ourSpring.springStrong.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ganxinming
 * @createDate 2021/7/22
 * @description
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TopicDataType {
	/**
	 * 策略类型
	 *
	 * @return
	 */
	String format();
}
