package com.ourgut.dubboAdvanceConsumer.controller;

import javax.annotation.Resource;

import com.springDubbo.dubboService.provider.DubboService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/7/1
 * @description
 */
@RestController
public class ConsumerCotroller {

	@Resource(name="dubboService1")
	DubboService dubboService;

	@RequestMapping("/test")
	public String test(){
		dubboService.serviceInvoke();
		return "success";
	}

}
