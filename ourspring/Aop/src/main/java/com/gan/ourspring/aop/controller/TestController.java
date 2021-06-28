package com.gan.ourspring.aop.controller;

import com.gan.ourspring.aop.annotation.ServiceLimit;

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

	@RequestMapping("/aroudLimit")
	@ServiceLimit
	public String aroudLimit(String name){
		System.out.println("name:"+name);
		return "测试AOP";
	}

	@RequestMapping("/aroud1")
	public String aroud(String taskName,int shardingTotalCount,String shardingItemList){
		System.out.println(taskName+shardingItemList+shardingTotalCount);
		return "测试AOP";
	}

}
