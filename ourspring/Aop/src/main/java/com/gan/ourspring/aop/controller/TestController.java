package com.gan.ourspring.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/1/5
 * @description
 */

@RestController
public class TestController {

	@RequestMapping("/test")
	public String test(String name){
		System.out.println("name:"+name);
		return "测试AOP";
	}

	@RequestMapping("/aroud")
	public String aroud(String name){
		System.out.println("name:"+name);
		return "测试AOP";
	}
}
