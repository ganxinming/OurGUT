package com.ourgut.design.pattern.structural.proxy.cglib;

import com.ourgut.design.pattern.common.Customer;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author ganxinming
 * @createDate 2022/2/11
 * @description
 *
 * 查看源码可以发现生成了三个class文件，新生成的类，重写了Customer所有方法。(一个是代理类，另外两个是被代理类和代理类的FastClass类)
 * CGLib动态代理执行代理方法的效率之所以比JDK高，是因为CGlib采用了FastClass机制，它的原理简单来说就是：为代理类和被代理类各生成一个类，
 * 这个类会为代理类或被代理类的方法分配一个index（int类型）；这个index被当作一个入参，FastClass可以直接定位要调用的方法并直接进行调用，
 * 省去了反射调用，因此调用效率比JDK代理通过反射调用高。(简单来说，实现了FisrtClass，可以定位到要调用的方法，比反射快)
 */
public class Test {
	public static void main(String[] args) throws Exception {

		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/ganxinming/myProjects/OurGUT/designPattern/target/classes/com/ourgut/design/pattern/common/");
		Customer instance = (Customer)new CGlibMeipo().getInstance(Customer.class);
		instance.findLove();
		System.out.println("==========");
		instance.toString();
	}
}
