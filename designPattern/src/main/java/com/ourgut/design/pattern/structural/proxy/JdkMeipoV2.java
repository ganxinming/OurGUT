package com.ourgut.design.pattern.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.ourgut.design.pattern.common.ICourse;
import com.ourgut.design.pattern.common.IPerson;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description
 * 动态代理升级通用版本：能代理所有任意接口，只需要保持内部引用为object就好了。
 */
public class JdkMeipoV2 implements InvocationHandler {

	private Object target;


	public Object getInstance(Object target){
		this.target = target;
		Class<?> clazz =  target.getClass();
		return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result=null;

		result= method.invoke(this.target, args);

		after();
		return result;
	}

	private void after() {
		System.out.println("双方同意，开始交往");
	}

	private void before() {
		System.out.println("我是媒婆，已经收集到你的需求，开始物色");
	}
}
