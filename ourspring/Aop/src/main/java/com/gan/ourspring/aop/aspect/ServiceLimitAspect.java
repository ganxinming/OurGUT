package com.gan.ourspring.aop.aspect;

import com.gan.ourspring.aop.config.LimitConfig;
import com.gan.ourspring.aop.service.IRateLimitService;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/6/18
 * @description
 */
@Aspect
@Component
@Slf4j
public class ServiceLimitAspect {

	private RateLimiter rateLimiter=RateLimiter.create(0.5);

	@Pointcut("@annotation(com.gan.ourspring.aop.annotation.ServiceLimit)")
	public void serviceLimit(){
	}


//	@Autowired IRateLimitService iRateLimitService;
//	@Autowired LimitConfig limitConfig;
	/**
	 * 简单限流
	 * @param joinPoint
	 * @return
	 */
	@Around("serviceLimit()")
	public Object around(ProceedingJoinPoint joinPoint){
		Object object=null;
		long start = System.currentTimeMillis();
		try {
			if(rateLimiter.tryAcquire()){
				object = joinPoint.proceed();
				log.info("success:{}",System.currentTimeMillis()-start);
			}else{
				double acquire = rateLimiter.acquire();
				log.warn("service invoke is block: {},job:{}",acquire,joinPoint.getSignature());
				object=joinPoint.proceed();
				if(log.isInfoEnabled()){
					log.info("success second:{}",System.currentTimeMillis()-start);
				}
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return object;
	}


//	@Around("serviceLimit()")
//	public Object around1(ProceedingJoinPoint joinPoint){
//		Object object=null;
//		try {
//			if(limitConfig.isLimitSwitch()){
//
//			}
//			else{
//				log.info("限流开关关闭");
//			}
//
//		} catch (Throwable throwable) {
//			throwable.printStackTrace();
//		}
//		return object;
//	}

}
