package com.gan.springuser.IOC.config;

import java.util.UUID;

import com.gan.springuser.IOC.entity.Student;
import com.gan.springuser.IOC.entity.Teacher;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author ganxinming
 * @createDate 2021/2/4
 * @description
 */
@Configuration
public class SpringBeanConfig {

	/**
	 * 多例默认懒加载,使用时才加入
	 * @return
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Student student(){
		int max=100,min=1;
		int ran2 = (int) (Math.random()*(max-min)+min);
		return Student.builder().name(String.valueOf(ran2)).build();
	}


	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Teacher teacher(){
		int max=100,min=1;
		int ran2 = (int) (Math.random()*(max-min)+min);
		return Teacher.builder().name(String.valueOf(ran2)).build();
	}

	/**
	 * 将其他需要的bean交给spring管理
	 * 这里只是随便用UUID
	 * @return
	 */
	@Bean
	public UUID uuid(){
		return new UUID(123,123);
	}

}
