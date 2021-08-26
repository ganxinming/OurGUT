package com.gan.java8.thread.produce.ThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

/**
 * @author ganxinming
 * @createDate 2021/3/29
 * @description
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CaseThreadPoolManager {

	private final static int CORE_NUM = Runtime.getRuntime().availableProcessors();

	private final static ThreadFactory CASE_THREAD_FACTORY = new BasicThreadFactory
			.Builder().namingPattern("case-threadPool-%d").daemon(true).build();

	private final static ThreadPoolExecutor punishCountThreadPool = new ThreadPoolExecutor(CORE_NUM,
			CORE_NUM * 2, 0, TimeUnit.MICROSECONDS,
			new LinkedBlockingQueue<>(100), CASE_THREAD_FACTORY,
			new ThreadPoolExecutor.AbortPolicy());

	public static void submitToCaseThreadPool(CommonTask commonTask){
		punishCountThreadPool.submit(()->{
			punishCountThreadPool.shutdownNow();
			commonTask.beforeExecute();
			try {
				commonTask.doExecute();
			} finally {
				commonTask.afterExecute();
			}
		});
	}
}

