package com.gan.springuser.IOC.controller;

import com.gan.springuser.IOC.entity.Student;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/2/4
 * @description
 */
@RestController
@RequiredArgsConstructor
public class TestIocController1 {

	private final Student student;

	/**
	 * 测试多例
	 * 仅仅只是Student注入多例无效，因为Controller是单例，那么注入进来的Student就已经确定了
	 * 将Student注入两个controller就可以发现不同了
	 * @return
	 */
	@RequestMapping("/getStudent1")
	public String getStudent(){
		System.out.println(student.hashCode());
		return student.getName();
	}
}
