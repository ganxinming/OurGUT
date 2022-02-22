package com.ourgut.design.pattern.structural.proxy.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.ourgut.design.pattern.common.IPerson;
import com.ourgut.design.pattern.common.ZhangSan;
import com.ourgut.design.pattern.structural.proxy.JdkMeipo;
import sun.misc.ProxyGenerator;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description JDK代理帮助我们生成的代理类，原理：进行字节码重组
 * 我们都知道JDK动态代理采用字节重组，重新生成对象来替代原始对象，以达到动态代理的目的。JDK动态代理生成对象的步骤如下。
 * （1）获取被代理对象的引用，并且获取它的所有接口，反射获取。
 * （2）JDK动态代理类重新生成一个新的类，同时新的类要实现被代理类实现的所有接口。
 * （3）动态生成Java代码，新加的业务逻辑方法由一定的逻辑代码调用（在代码中体现）。
 * （4）编译新生成的Java代码.class文件。
 * （5）重新加载到JVM中运行。
 * 以上过程就叫作字节码重组。JDK中有一个规范，在ClassPath下只要是$开头的.class文件，一般都是自动生成的。
 * 那么有没有办法看到代替后的对象的“真容”呢？做一个这样的测试，将内存中的对象字节码通过文件流输出到一个新的.class文件，
 * 然后使用反编译工具查看源码。
 *
 * 我们发现，$Proxy0继承了Proxy类，同时实现了Person接口，而且重写了findLove()等方法。
 * 在静态代码块中用反射查找到了目标对象的所有方法
 * ，而且保存了所有方法的引用，重写的方法用反射调用目标对象的方法。
 *
 * 重组字节码：
 * 1.保存目标类原来所有方法引用
 * 2.重写原来所有方法，改成调用invoke方法(即InvocationHandler的invoke方法，此方法可以在方法执行前后进行操作)，将原方法引用传入。
 */
public class JDKProxy {
	public static void main(String[] args) throws Exception {
		IPerson obj = (IPerson)new JdkMeipo().getInstance(new ZhangSan());
		obj.findLove();

		//通过反编译工具查看生成的代理类的源代码
		byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{IPerson.class});
		FileOutputStream os = new FileOutputStream("/Users/ganxinming/myProjects/OurGUT/designPattern/target/classes/com/ourgut/design/pattern/common/$Proxy0.class");
		os.write(bytes);
		os.close();
	}
}
