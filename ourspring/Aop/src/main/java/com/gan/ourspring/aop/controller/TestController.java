package com.gan.ourspring.aop.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.gan.ourspring.aop.annotation.MethodAnno;
import com.gan.ourspring.aop.annotation.RateLimiterStrong;
import com.gan.ourspring.aop.annotation.ServiceLimit;
import com.gan.ourspring.aop.entity.Family;
import com.gan.ourspring.aop.entity.UserResponse;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/1/5
 * @description
 */

@RestController
@Slf4j
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

	@RateLimiterStrong(value = 0.5, timeout = 1)
	@GetMapping("/test1")
	public String test1() {
		log.info("【test1】被执行了。。。。。");
		return JSONUtil.toJsonStr(Dict.create().set("msg", "hello,world!").set("description", "别想一直看到我，不信你快速刷新看看~"));
	}

	@MethodAnno(getMethName = "kk")
	@RequestMapping("/t1")
	public UserResponse t1(String clazzName,Integer age){
		System.out.println(clazzName+age);
		UserResponse userResponse=new UserResponse();
		userResponse.setAge(12);
		userResponse.setList(Lists.newArrayList(1,2));
		userResponse.setMap(ImmutableMap.of("a", 1, "b", 2, "c", 3));
		userResponse.setName("ming");
		userResponse.setFamily(new Family("gan"));
		return userResponse;
	}
}
