package com.gan.ourspring.hystrix.web;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.gan.ourspring.hystrix.service.ConfigUtils;
import com.gan.ourspring.hystrix.service.impl.HttpHystrix;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
@RestController
public class TestHystrixController {

	@RequestMapping("/executeTest")
	public String testHystrixExecute(){
		HttpHystrix httpHystrix=new HttpHystrix("HttpHystrix", ConfigUtils.defaultThreadPoolConfig());
		System.out.println(httpHystrix.execute());
		System.out.println("先做自己的事");
		return "hystrix";
	}
	@RequestMapping("/queueTest")
	public String testHystrixQueue() throws ExecutionException, InterruptedException {
		HttpHystrix httpHystrix=new HttpHystrix("HttpHystrix", ConfigUtils.defaultThreadPoolConfig());
		Future queue = httpHystrix.queue();
		System.out.println("先做自己的事");
		System.out.println(queue.get());
		return "hystrix";
	}




}
