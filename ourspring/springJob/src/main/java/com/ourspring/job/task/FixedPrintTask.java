package com.ourspring.job.task;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/9/3
 * @description
 */
@Component
@Slf4j
public class FixedPrintTask {
	private int i;

	@Scheduled(cron = "*/1 * * * * ?")
	@Async//可以保证异步处理，不用等待上一个任务处理完成
	public void execute() throws InterruptedException {
		Thread.sleep(10000L);
		log.info("thread id:{},FixedPrintTask execute times:{}", Thread.currentThread().getId(), ++i);
	}

}
