package com.gan.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author ganxinming
 * @createDate 2021/6/18
 * @description 限流 令牌桶
 */
public class TestRateLimiter {

	public static void main(String[] args) {
		RateLimiter limiter=RateLimiter.create(10);
		while (true){
			System.out.println(limiter.acquire());
		}
	}
}
