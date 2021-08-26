package com.gan.java8.thread.produce.ThreadPool;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/8/23
 * @description
 */
@Data
public class TestRunable implements Runnable{
	private String id;

	public TestRunable(String id) {
		this.id = id;
	}

	@Override
	public void run() {
		Thread.currentThread().setName(id);
		System.out.println("线程开始运行："+Thread.currentThread().getName());
	}
}
