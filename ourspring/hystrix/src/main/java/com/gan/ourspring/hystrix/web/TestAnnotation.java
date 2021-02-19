package com.gan.ourspring.hystrix.web;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.gan.ourspring.hystrix.service.ConfigUtils;
import com.gan.ourspring.hystrix.service.impl.HttpHystrix;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/1/12
 * @description
 */
@RestController
public class TestAnnotation {

	@RequestMapping("/testAnnotation")
	@HystrixCommand(commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "500")
	},fallbackMethod = "fail"
	)
	public String testAnnotation() throws ExecutionException, InterruptedException {
		System.out.println("开始调用");
		Thread.sleep(1000);
		System.out.println("测试huystrix");
		return "hystrix";
	}


	private String fail(){
		System.out.println("熔断了");
		return "fail";
	}
}
