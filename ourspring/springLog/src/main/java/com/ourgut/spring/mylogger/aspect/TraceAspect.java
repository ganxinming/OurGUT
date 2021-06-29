package com.ourgut.spring.mylogger.aspect;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;

import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/6/29
 * @description
 */
@Aspect
@Component
@Slf4j
public class TraceAspect {

	@Pointcut("execution(public * com.ourgut.spring.mylogger.controller.*.*(..))")
	public void trace(){

	}

	//在所有controller中加上链路追踪
	@Before("trace()")
	public void before(){
		MDC.put("traceId", UUID.randomUUID().toString());// 加入trace追踪
	}

}
