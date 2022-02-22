package com.ourgut.design.pattern.structural.proxy.core;

import java.lang.reflect.Method;

/**
 * @author ganxinming
 * @createDate 2022/2/7
 * @description 模仿InvocationHandler
 */
public interface GPInvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable;
}

