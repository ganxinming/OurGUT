package com.ourGUT.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ganxinming
 * @createDate 2021/5/17
 * @description 基于ScheduledExecutorService实现定时任务
 */
public class ScheduleTest {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		scheduledExecutorService.scheduleAtFixedRate(()->{
			System.out.println(123);
		},1,1, TimeUnit.SECONDS);
	}
}
