package com.ourGut.ourSpring.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.ourGut.ourSpring.springmvc.entity.MyUser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/8/17
 * @description
 */
@RestController
public class TestJson {

	@RequestMapping("/test/json")
	public String testjson(@Valid MyUser user){
		System.out.println(user);
		return JSON.toJSONString(user);
	}
}
