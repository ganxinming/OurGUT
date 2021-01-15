package com.gan.ourspring.hystrix.service;

import java.util.Objects;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
@Data
public class HystrixThreadPoolConfig {

	/**
	 * 核心线程池大小
	 */
	private Integer corePoolSize;

	/**
	 * 最大线程池大小
	 */
	private Integer maxPoolSize;

	/**
	 * 线程池执行计算超时时间
	 */
	private Integer executionTimeOut;

	/**
	 * 空闲线程存活时间
	 */
	private Integer keepAliveTime;

	/**
	 * 线程池最大队列大小
	 */
	private Integer maxQueueSize;

	/**
	 * 队列拒绝任务数大小
	 */
	private Integer queueRejectSize;

	/**
	 * 熔断器进行d熔断错误比率计算请求阈值
	 */
	private Integer circuitBreakerRequestVolumeThreshold;

	/**
	 * 熔断器关闭到打开错误百分比
	 */
	private Integer circuitBreakerErrorThresholdPercent;

	/**
	 * 熔断器打开到关闭的时间窗长度
	 */
	private Integer circuitBreakerSleepWindowTime;

	/**
	 * 统计滚动的时间窗口
	 */
	private Integer metricsRollingStatisticWindowTime;

	/**
	 * 失败回调fallBack方法直达并发请求数
	 */
	private Integer fallBackMaxConRequest;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		HystrixThreadPoolConfig that = (HystrixThreadPoolConfig) o;
		return Objects.equals(corePoolSize, that.corePoolSize) &&
				Objects.equals(maxPoolSize, that.maxPoolSize) &&
				Objects.equals(executionTimeOut, that.executionTimeOut) &&
				Objects.equals(keepAliveTime, that.keepAliveTime) &&
				Objects.equals(maxQueueSize, that.maxQueueSize) &&
				Objects.equals(queueRejectSize, that.queueRejectSize) &&
				Objects.equals(circuitBreakerRequestVolumeThreshold, that.circuitBreakerRequestVolumeThreshold) &&
				Objects.equals(circuitBreakerErrorThresholdPercent, that.circuitBreakerErrorThresholdPercent) &&
				Objects.equals(circuitBreakerSleepWindowTime, that.circuitBreakerSleepWindowTime) &&
				Objects.equals(metricsRollingStatisticWindowTime, that.metricsRollingStatisticWindowTime) &&
				Objects.equals(fallBackMaxConRequest, that.fallBackMaxConRequest);
	}

	@Override
	public int hashCode() {
		return Objects.hash(corePoolSize, maxPoolSize, executionTimeOut, keepAliveTime, maxQueueSize, queueRejectSize, circuitBreakerRequestVolumeThreshold, circuitBreakerErrorThresholdPercent, circuitBreakerSleepWindowTime, metricsRollingStatisticWindowTime, fallBackMaxConRequest);
	}
}
