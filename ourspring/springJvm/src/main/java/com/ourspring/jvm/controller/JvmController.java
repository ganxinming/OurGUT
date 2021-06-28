package com.ourspring.jvm.controller;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ourspring.jvm.entity.JvmEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/6/15
 * @description
 */
@RestController
public class JvmController {

	@RequestMapping("/test")
	public String test(){
		return "jvm";
	}

	@RequestMapping("/test1")
	public String test1() throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(50);
		executorService.submit(()->{
			int i=0;
			while(true){

				JvmEntity entity=JvmEntity.builder().name(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name2(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name1(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name3(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name4(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name5(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString())
						.name6(UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()).build();
//				System.out.println(Thread.currentThread().getName()+"运行次数:"+i+"对象为："+entity.toString());
				Thread.sleep(100);
			}
		});
		return "jvm";
	}
}
