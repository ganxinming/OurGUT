package com.gan.ourspring.hystrix.service.impl;

import java.util.concurrent.Future;

import com.gan.ourspring.hystrix.service.AbstractIsolationThreadPool;
import com.gan.ourspring.hystrix.service.HystrixThreadPoolConfig;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
public class HttpHystrix  extends AbstractIsolationThreadPool {
	public HttpHystrix(String name, HystrixThreadPoolConfig poolConfig) {
		super(name, poolConfig);
	}

	@Override
	protected Object doExecute() {
		System.out.println("HttpHystrix调用doExecute");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object execute() {
		System.out.println("HttpHystrixExecute");
		return super.execute();
	}

	@Override
	public Future queue() {
		System.out.println("HttpHystrixQueue");
		return super.queue();
	}

	/**
	 * 熔断调用
	 * @return
	 */
	@Override
	protected Object getFallback() {
		System.out.println("HttpHystrix熔断了getFallback");
		return "熔断了";
	}
}
