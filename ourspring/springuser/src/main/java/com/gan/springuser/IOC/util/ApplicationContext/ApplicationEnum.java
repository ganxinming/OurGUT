package com.gan.springuser.IOC.util.ApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ganxinming
 * @createDate 2021/2/5
 * @description 针对老项目，含有xml文件，获取ApplicationContext
 */
public enum ApplicationEnum {

	APPLICATIONCONTEXT;

	private ApplicationContext applicationContext = null;

	ApplicationEnum(){
		applicationContext= new ClassPathXmlApplicationContext("spring-bean.xml");
	}

	public ApplicationContext getApplicationContext(){
		return applicationContext;
	}
}
