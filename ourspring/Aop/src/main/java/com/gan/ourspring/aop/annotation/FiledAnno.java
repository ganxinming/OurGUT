package com.gan.ourspring.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ganxinming
 * @createDate 2021/9/16
 * @description 作用于字段上
 */
@Inherited
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FiledAnno {
	//通过类直接访问
	int VALUE = 0;
	String NAME="";
	//方法，通过注解对象访问
	int value() default VALUE;
	String name() default NAME;
}
