package com.ourgut.design.pattern.structural.proxy;

import com.ourgut.design.pattern.common.ICourse;
import com.ourgut.design.pattern.common.IPerson;
import com.ourgut.design.pattern.common.JavaCourse;
import com.ourgut.design.pattern.common.ZhangSan;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description 动态代理：不同于静态代理，静态代理需要知道具体实现的接口，而动态代理无需知道具体的接口，代理者只需实现InvocationHandler
 * 1.实现的invoke方法，其实就是未知接口的所有方法在调用时，都会调用invoke方法
 * 2.提供获取代理对象的方法，然后代理对象执行方法，自动调用invoke方法
 *
 *
 * 作用：同静态代理，为代理者调用方法前后，实现逻辑。
 * 特点：无需实现特定的代理对象的接口。
 */

public class DynamicProxy {
	public static void main(String[] args) {
		/**
		 * 一个代理类，代理两种对象
		 */
		JdkMeipo jdkMeipo=new JdkMeipo();

		IPerson instance = jdkMeipo.getInstance(new ZhangSan());
		instance.findLove();
		instance.startLove();

		ICourse instance1 = jdkMeipo.getInstance1(new JavaCourse());
		instance1.record();

		System.out.println("#########################");

		/**
		 * 代理对象，直接无视代理接口，能代理任意接口
		 */
		JdkMeipo jdkMeipo1=new JdkMeipo();

		IPerson instance12 = jdkMeipo1.getInstance(new ZhangSan());
		instance12.findLove();
		instance12.startLove();

		ICourse instance13 = jdkMeipo1.getInstance1(new JavaCourse());
		instance13.record();
		System.out.println("##############");
		System.out.println("证明调用其他所有普通方法也是，通过调用invoke方法去实现的");
		instance.toString();
	}
}
