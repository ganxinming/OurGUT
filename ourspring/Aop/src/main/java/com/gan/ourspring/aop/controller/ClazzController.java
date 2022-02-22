package com.gan.ourspring.aop.controller;

import com.gan.ourspring.aop.annotation.MethodAnno;
import com.gan.ourspring.aop.entity.Family;
import com.gan.ourspring.aop.entity.UserResponse;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/9/16
 * @description 反射类进行注解
 */
@RestController("/clazz/")
public class ClazzController {

//	@MethodAnno(getMethName = "kk")
//	@RequestMapping("/t1")
//	public UserResponse test1(String clazzName,Integer age){
//		System.out.println(clazzName+age);
//		UserResponse userResponse=new UserResponse();
//		userResponse.setAge(12);
//		userResponse.setList(Lists.newArrayList(1,2));
//		userResponse.setMap(ImmutableMap.of("a", 1, "b", 2, "c", 3));
//		userResponse.setName("ming");
//		userResponse.setFamily(new Family("gan"));
//		return userResponse;
//	}
}
