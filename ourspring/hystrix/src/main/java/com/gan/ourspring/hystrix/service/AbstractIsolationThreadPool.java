package com.gan.ourspring.hystrix.service;

import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
@Slf4j
public abstract class AbstractIsolationThreadPool<T> extends HystrixCommand<T> {

	private final static String SUFFIX_GROUP_KEY = "_Group";

	private final static String SUFFIX_COMMAND_KEY = "_commandKey";

	private final static String SUFFIX_THREAD_POOL_KEY = "_threadPool";

	private final static ThreadLocal<Long> BEGIN_TIME = ThreadLocal.withInitial(System::currentTimeMillis);

	protected final static ThreadLocal<Long> EXECUTE_TIME_WATCHER = ThreadLocal.withInitial(System::currentTimeMillis);

	public AbstractIsolationThreadPool(String name, HystrixThreadPoolConfig poolConfig) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name + SUFFIX_GROUP_KEY))
				.andCommandKey(HystrixCommandKey.Factory.asKey(name + SUFFIX_COMMAND_KEY))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name + SUFFIX_THREAD_POOL_KEY))
				.andCommandPropertiesDefaults(
						HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(poolConfig.getExecutionTimeOut())
								.withFallbackEnabled(true)
								//fallBack最大并发数
								.withFallbackIsolationSemaphoreMaxConcurrentRequests(poolConfig.getFallBackMaxConRequest())
								//线程隔离
								.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
								//熔断器配置
								.withCircuitBreakerEnabled(true)
								.withCircuitBreakerRequestVolumeThreshold(poolConfig.getCircuitBreakerRequestVolumeThreshold())
								.withCircuitBreakerErrorThresholdPercentage(poolConfig.getCircuitBreakerErrorThresholdPercent())
								.withCircuitBreakerSleepWindowInMilliseconds(poolConfig.getCircuitBreakerSleepWindowTime())
								.withMetricsRollingStatisticalWindowInMilliseconds(poolConfig.getMetricsRollingStatisticWindowTime())
				)
				.andThreadPoolPropertiesDefaults(
						//线程池配置
						HystrixThreadPoolProperties.Setter()
								.withCoreSize(poolConfig.getCorePoolSize())
//								.withMaximumSize(poolConfig.getMaxPoolSize())
								.withMaxQueueSize(poolConfig.getMaxQueueSize())
								.withKeepAliveTimeMinutes(poolConfig.getKeepAliveTime())
								.withQueueSizeRejectionThreshold(poolConfig.getQueueRejectSize())
//								.withAllowMaximumSizeToDivergeFromCoreSize(true))
		));
	}

	/**
	 * 实际调用run方法
	 * 同步返回
	 * @return
	 */
	@Override
	public T execute() {
		return super.execute();
	}

	/**
	 * 实际调用run方法
	 * 异步返回
	 * @return
	 */
	@Override
	public Future<T> queue() {
		return super.queue();
	}

	@Override
	protected T run() throws Exception {
		before();
		doExecute();
		after();
		return (T) "处理成功";
	}

	/**
	 * 对于想让子类各自实现的使用abstract
	 * @return
	 */
	protected abstract T doExecute();

	/**
	 * 对于想让子类统一实现的，就普通就行
	 */
	protected void before(){
		log.info("before");
	}

	protected void after(){
		log.info("after");
	}
}
