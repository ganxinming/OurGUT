package com.ourgut.design.pattern.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.ourgut.design.pattern.common.ICourse;
import com.ourgut.design.pattern.common.IPerson;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description 动态代理对象
 *
 * 采用动态代理基本上只要是人（IPerson）就可以提供相亲服务。动态代理的底层实现一般不用我们亲自去实现，
 * 已经有很多现成的API。在Java生态中，目前普遍使用的是JDK自带的代理和CGLib提供的类库。
 * 实现InvocationHandler，实现invoke方法，提供产生代理对象的方法
 *

 */
public class JdkMeipo implements InvocationHandler {
	private IPerson target;
	private ICourse iCourse;


	public IPerson getInstance(IPerson target){
		this.target = target;
		Class<?> clazz =  target.getClass();
		return (IPerson) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
	}

	public ICourse getInstance1(ICourse target){
		this.iCourse = target;
		Class<?> clazz =  target.getClass();
		return (ICourse) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
	}

	/**
	 * invoke 方法，对于代理者IPerson接口中的每一个方法，都会调用一次invoke
	 * 如果想针对method做特殊处理，可以判判断具体的方法名,通过不同的方法名，去调用不同的代理对象
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result=null;

		if(method.getName().equals("findLove") || method.getName().equals("startLove")){
			result= method.invoke(this.target, args);
		}
		if(method.getName() == "record"){
			result = method.invoke(this.iCourse, args);
		}
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
