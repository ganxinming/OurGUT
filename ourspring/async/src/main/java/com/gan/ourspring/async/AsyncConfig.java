package com.gan.ourspring.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ganxinming
 * @createDate 2020/12/5
 * @description
 */
@Configuration
public class AsyncConfig {

	private static final int MAX_POOL_SIZE = 50;

	private static final int CORE_POOL_SIZE = 20;

	/**
	 * 自定义任务线程池
	 * @return
	 */
	@Bean("taskExecutor")
	public AsyncTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
		taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
		taskExecutor.setThreadNamePrefix("async-task-thread-pool");
		taskExecutor.initialize();
		System.out.println("线程池初始化完毕...");
		return taskExecutor;
	}
}
