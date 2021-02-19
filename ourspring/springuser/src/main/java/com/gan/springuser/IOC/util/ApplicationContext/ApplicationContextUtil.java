package com.gan.springuser.IOC.util.ApplicationContext;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/2/5
 * @description 直接注入ApplicationContext使用，最简单
 */
@Component
public class ApplicationContextUtil {

	@Resource
	private ApplicationContext ctx;

	public Object getBean(String name){
		return ctx.getBean(name);
	}

	public <T>T getBean(Class<T> clazz){
		return ctx.getBean(clazz);
	}

	public <T> T getBean(Class<T> clazz,String name){
		return ctx.getBean(name,clazz);
	}
}
