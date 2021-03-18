package com.ourspring.springdubboconsumer.invoker;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springDubbo.dubboService.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/3/17
 * @description
 */
@RestController
public class TestInvokeController {

	@Autowired
	TestService testService;

	@RequestMapping("/invokeServiceImpl")
	public String invokeServiceImpl(){
		String s = testService.startInvoke();
		return s;
	}
}
