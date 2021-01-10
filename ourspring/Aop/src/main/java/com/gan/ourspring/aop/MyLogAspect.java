package com.gan.ourspring.aop;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author ganxinming
 * @createDate 2021/1/5
 * @description
 */

@Aspect
@Component
@Slf4j
public class MyLogAspect {

	private final static ThreadLocal<Long> TIME_CONSUMER_WATCHER = ThreadLocal.withInitial(System::currentTimeMillis);

	@Pointcut("execution(public * com.gan.ourspring.aop.controller..*.*(..))")
	public void controllerLog(){}



	@Before("controllerLog()")
	public void beforeControllerLog(JoinPoint joinPoint){
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
		HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
		log.info(requestAttributes.toString());
		log.info(request.toString());
		log.info("切面before");
	}

	@Around("controllerLog()")
	public String aroundControllerLog(ProceedingJoinPoint joinPoint){
		String name = joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature()
						.getName();
		log.info(name);
		try {
			Object result = joinPoint.proceed();
			log.info(result.toString());
			return (String) result;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return  "空";
	}

	@PostConstruct
	private void init() throws Exception {
		TIME_CONSUMER_WATCHER.set(System.currentTimeMillis());
		log.info("aspect开始初始化，在构造方法之后");
		log.info("初始化时间"+String.valueOf(System.currentTimeMillis()-TIME_CONSUMER_WATCHER.get()));
	}
}
