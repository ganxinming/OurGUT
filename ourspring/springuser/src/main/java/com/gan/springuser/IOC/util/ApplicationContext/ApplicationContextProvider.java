package com.gan.springuser.IOC.util.ApplicationContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/2/5
 * @description 实现ApplicationContextAware，获取applicationContext对象
 */

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	public Object getBean(String name){
		return getApplicationContext().getBean(name);
	}

	public <T>T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}

	public <T> T getBean(Class<T> clazz,String name){
		return getApplicationContext().getBean(name,clazz);
	}
}
