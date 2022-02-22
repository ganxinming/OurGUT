package com.ourgut.design.pattern.structural.proxy.core;

import java.lang.reflect.Method;

import com.ourgut.design.pattern.common.IPerson;
import com.ourgut.design.pattern.common.ZhangSan;

/**
 * @author ganxinming
 * @createDate 2022/2/7
 * @description 手动实现JDK代理
 * 感悟：如果要实现jdk代理大概两个方面
 *
 * 1.实现InvocationHandler的invoke()方法[jdk生成的新的对象的原来所有方法，都是调用this.h.invoke(this,m方法)所以这个invoke方法，相当于所有方法调用的模板]
 * 2.获取jdk代理后的对象getInstance()[通过字符串拼接，生成新的类.class文件，然后进行编译成字节码，加载进入jvm]
 */
public class GpMeipo implements GPInvocationHandler {
	private IPerson target;
	public IPerson getInstance(IPerson target){
		this.target = target;
		Class<?> clazz =  target.getClass();
		return (IPerson) GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(this.target,args);
		after();
		return result;
	}

	private void after() {
		System.out.println("双方同意，开始交往");
	}

	private void before() {
		System.out.println("我是媒婆，已经收集到你的需求，开始物色");
	}

	public static void main(String[] args) {
		/**
		 * 手动实现JDK代理
		 */
		GpMeipo gpMeipo = new GpMeipo();
		IPerson zhangsan = gpMeipo.getInstance(new ZhangSan());
		zhangsan.findLove();
	}
}
