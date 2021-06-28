package com.gan.ourspring.aop.service;

import java.util.concurrent.TimeUnit;

import com.gan.ourspring.aop.config.LimitConfig;
import com.google.common.util.concurrent.RateLimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/6/18
 * @description
 */
//@Service
public class IRateLimitServiceImpl implements IRateLimitService{

//	@Autowired
	private LimitConfig config;

	private  RateLimiter rateLimiter;

	public IRateLimitServiceImpl(){
		this.rateLimiter=RateLimiter.create(config.getLimitMax());
	}

	@Override
	public boolean tryAcquire() {

		return rateLimiter.tryAcquire();
	}

	@Override
	public boolean tryAcquire(int permits) {
		return rateLimiter.tryAcquire(permits);
	}

	@Override
	public boolean acquire(long timeout) {
		return this.acquire(1, timeout);
	}

	@Override
	public boolean acquire(int permits, long timeout) {
		boolean tryAcquire = rateLimiter.tryAcquire(permits, timeout, TimeUnit.MILLISECONDS);
		if(tryAcquire){
			return true;
		}
		return false;
	}
}
