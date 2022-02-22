package com.ourgut.design.pattern.structural.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author ganxinming
 * @createDate 2022/2/11
 * @description cglib不需要实现接口，它是通过动态继承目标对象实现动态代理的
 *
 * 对比：
 * 1）JDK动态代理实现了被代理对象的接口，CGLib动态代理继承了被代理对象。
 * （2）JDK动态代理和CGLib动态代理都在运行期生成字节码，JDK动态代理直接写Class字节码，CGLib动态代理使用ASM框架写Class字节码。CGLib动态代理实现更复杂，生成代理类比JDK动态代理效率低。
 * （3）JDK动态代理调用代理方法是通过反射机制调用的，CGLib动态代理是通过FastClass机制直接调用方法的，CGLib动态代理的执行效率更高。
 *
 * 总结：1.一个需要接口，一个不需要
 * 2.一个生成代理类效率高，一个效率低
 * 3.一个通过反射调方法执行效率低，一个通过fastclass机制执行效率高
 */
public class CGlibMeipo implements MethodInterceptor {

	/**
	 * 几乎和JDK动态代理一样，也是生成一个代理类
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public Object getInstance(Class<?> clazz) throws Exception{
		//相当于JDK中的Proxy类，是完成代理的工具类
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		before();
		Object obj = methodProxy.invokeSuper(o,objects);
		after();
		return obj;
	}

	private void before(){
		System.out.println("我是媒婆，我要给你找对象，现在已经确认你的需求");
		System.out.println("开始物色");
	}

	private void after(){
		System.out.println("双方同意，准备办婚事");
	}
}
