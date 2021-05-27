package com.ourGut.ourSpring.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/4/29
 * @description
 */
@RestController
public class TestVue {

	@RequestMapping("/test/vue")
	public String getVue(){
		Map map=new HashMap();
		map.put("gan","123");
		return JSON.toJSONString(map);
	}

	@RequestMapping("/test/vue123")
	public String getVue123(){
		Map map=new HashMap();
		map.put("gan","123");
		return JSON.toJSONString(map);
	}
}
