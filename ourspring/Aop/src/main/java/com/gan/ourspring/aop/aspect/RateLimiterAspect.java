package com.gan.ourspring.aop.aspect;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.gan.ourspring.aop.annotation.RateLimiterStrong;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/6/29
 * @description
 */
@Slf4j
@Aspect
@Component
public class RateLimiterAspect {
	private static final ConcurrentMap<String, RateLimiter> RATE_LIMITER_CACHE = new ConcurrentHashMap<>();

	@Pointcut("@annotation(com.gan.ourspring.aop.annotation.RateLimiterStrong)")
	public void rateLimit() {

	}

	@Around("rateLimit()")
	public Object pointcut(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		// 通过 AnnotationUtils.findAnnotation 获取 RateLimiter 注解可以拿到注解参数
		RateLimiterStrong rateLimiter = AnnotationUtils.findAnnotation(method, RateLimiterStrong.class);
		if (rateLimiter != null && rateLimiter.qps() > RateLimiterStrong.NOT_LIMITED) {
			double qps = rateLimiter.qps();
			if (RATE_LIMITER_CACHE.get(method.getName()) == null) {
				// 初始化 QPS
				RATE_LIMITER_CACHE.put(method.getName(), RateLimiter.create(qps));
			}

			log.debug("【{}】的QPS设置为: {}", method.getName(), RATE_LIMITER_CACHE.get(method.getName()).getRate());
			// 尝试获取令牌
			if (RATE_LIMITER_CACHE.get(method.getName()) != null && !RATE_LIMITER_CACHE.get(method.getName()).tryAcquire(rateLimiter.timeout(), rateLimiter.timeUnit())) {
				throw new RuntimeException("手速太快了，慢点儿吧~");
			}
		}
		return point.proceed();
	}
}
