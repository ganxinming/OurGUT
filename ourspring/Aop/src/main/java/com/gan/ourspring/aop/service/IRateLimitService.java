package com.gan.ourspring.aop.service;

/**
 * @author ganxinming
 * @createDate 2021/6/18
 * @description
 */
public interface IRateLimitService {
	/**
	 * 尝试获取许可证，获取1个，立即返回非阻塞
	 *
	 * @return
	 */
	boolean tryAcquire();
	/**
	 * 尝试获取多个许可证，立即返回非阻塞
	 *
	 * @param permits
	 * @return
	 */
	boolean tryAcquire(int permits);
	/**
	 * 阻塞获取许可证，获取1个，若超过timeout未获取到许可证，则返回false
	 *
	 * @param timeout
	 * @return
	 */
	boolean acquire(long timeout);
	/**
	 * 阻塞获取多个许可证，若超过timeout未获取到许可证，则返回false
	 *
	 * @param permits
	 * @param timeout
	 * @return
	 */
	boolean acquire(int permits, long timeout);

}
