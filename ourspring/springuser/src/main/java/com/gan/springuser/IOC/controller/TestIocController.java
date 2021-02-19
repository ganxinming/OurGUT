package com.gan.springuser.IOC.controller;

import com.gan.springuser.IOC.entity.Student;
import com.gan.springuser.IOC.properties.ReadProperties;
import com.gan.springuser.IOC.properties.YmlDemo;
import com.gan.springuser.IOC.util.ApplicationContext.ApplicationContextProvider;
import com.gan.springuser.IOC.util.ApplicationContext.ApplicationContextUtil;
import com.gan.springuser.IOC.util.ApplicationContext.ApplicationEnum;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/2/4
 * @description
 */
@RestController
@RequiredArgsConstructor
public class TestIocController {

	private final Student student;


	private final ReadProperties properties;

	private final YmlDemo ymlDemo;


	private final ApplicationContextProvider provider;

	private final ApplicationContextUtil applicationContextUtil;

	/**
	 * 测试多例
	 * 仅仅只是Student注入多例无效，因为Controller是单例，那么注入进来的Student就已经确定了
	 * 将Student注入两个controller就可以发现不同了
	 * @return
	 */
	@RequestMapping("/getStudent")
	public String getStudent(){
		System.out.println(student.hashCode());
		return student.getName();
	}

	/**
	 * 测试读取配置文件
	 * @return
	 */
	@RequestMapping("/readProperties")
	public String readProperties(){
		return properties.getAValue();
	}

	/**
	 * 测试读取配置文件
	 * @return
	 */
	@RequestMapping("/ymlDemo")
	public String readymlDemo(){
		return ymlDemo.toString();
	}

	/**
	 * 测试获取 Application对象，获取bean
	 * @return
	 */
	@RequestMapping("/getApplication")
	public String getApplication(){
		Student bean = provider.getBean(Student.class);
		System.out.println(bean.toString());
		Student bean1 = applicationContextUtil.getBean(Student.class);
		System.out.println(bean1.toString());
		//由于没有配置xml所以关闭这种方式
//		Student bean2 = ApplicationEnum.APPLICATIONCONTEXT.getApplicationContext().getBean(Student.class);
		return bean1.toString();
	}

}
