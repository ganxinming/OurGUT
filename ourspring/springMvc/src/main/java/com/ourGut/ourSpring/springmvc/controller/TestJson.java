package com.ourGut.ourSpring.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ourGut.ourSpring.springmvc.entity.MyUser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/8/17
 * @description
 */
@RestController
public class TestJson {

	@Value("${spring.port:/abc:123}")
	private String name;

	@RequestMapping("/test/json")
	public String testjson(@Valid MyUser user){
		System.out.println(user);
		return JSON.toJSONString(user);
	}

	@RequestMapping(value = "/test/jsonUser",method = RequestMethod.POST)
	public String testjsonUser(@RequestBody JSONObject parry){
		JSONArray jsonArray = parry.getJSONArray("list");
		List<MyUser> myUsers = jsonArray.toJavaList(MyUser.class);
		Integer type1 = parry.getInteger("type");
		return "123";
	}

	@RequestMapping("/test")
	public String test(@RequestBody String name,@RequestBody String age){
		System.out.println(name);
		System.out.println(age);
		return "123";
	}

	@RequestMapping("/abc")
	public String abc(){
		System.out.println(name);
		return name;
	}

}
