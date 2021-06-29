package com.gan.ourspring.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import org.springframework.core.annotation.AliasFor;

/**
 * @author ganxinming
 * @createDate 2021/6/29
 * @description 根据不同端，设置不同限流
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimiterStrong {
	int NOT_LIMITED = 0;

	/**
	 * qps
	 * 用到注解 属性上，表示两个属性互相为别名，互相为别名的属性值（不是属性名）必须相同，若设置成不同，则会报错
	 */
	@AliasFor("qps") double value() default NOT_LIMITED;

	/**
	 * qps
	 */
	@AliasFor("value") double qps() default NOT_LIMITED;

	/**
	 * 超时时长
	 */
	int timeout() default 0;

	/**
	 * 超时时间单位
	 */
	TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
