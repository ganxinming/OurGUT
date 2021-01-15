package com.gan.ourspring.hystrix.service;


/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
public class ConfigUtils {


	private final static int PROCESSOR_NUM = Runtime.getRuntime().availableProcessors();


	/**
	 * 默认线程池配置
	 *
	 * @return
	 */
	 public static HystrixThreadPoolConfig defaultThreadPoolConfig() {
		HystrixThreadPoolConfig config = new HystrixThreadPoolConfig();
		config.setCorePoolSize(PROCESSOR_NUM * 2 + 1);
		config.setMaxPoolSize(PROCESSOR_NUM * 2 + 1);
		config.setKeepAliveTime(1);
		config.setExecutionTimeOut(160);
		config.setMaxQueueSize(100);
		config.setQueueRejectSize(100);
		config.setCircuitBreakerRequestVolumeThreshold(10);
		config.setCircuitBreakerErrorThresholdPercent(10);
		config.setCircuitBreakerSleepWindowTime(2000);
		config.setMetricsRollingStatisticWindowTime(60000);
		config.setFallBackMaxConRequest(10000);
		return config;
	}

}
