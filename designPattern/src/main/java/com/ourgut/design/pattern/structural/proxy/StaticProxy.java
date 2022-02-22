package com.ourgut.design.pattern.structural.proxy;

import com.ourgut.design.pattern.common.ZhangLaosan;
import com.ourgut.design.pattern.common.ZhangSan;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description 静态代理，只能代理某个确定的对象
 *
 * 作用：感觉代理者，就是相当于帮助实现者进行一些操作，前后置处理，或者一些其他多出来的操作。
 * 其实就是对原来类进行一些扩展操作
 *
 * 特点：代理者，和代理对象，实现同一接口。代理者，在构造中就注入代理对象，且调用具体方法时，调用代理对象的具体方法
 *
 * 采用动态代理基本上只要是人（IPerson）就可以提供相亲服务。动态代理的底层实现一般不用我们亲自去实现，已经有很多现成的API。
 * 在Java生态中，目前普遍使用的是JDK自带的代理和CGLib提供的类库。首先基于JDK的动态代理支持来升级一下代码。
 */
public class StaticProxy {
	public static void main(String[] args) {
		/**
		 * 张老三只能满足张三的需求，其他人的不能满足
		 * 需要一个更加通用的解决方案，满足任何单身人士找对象的需求
		 * 这就由静态代理升级到了动态代理。
		 */
		ZhangLaosan zhangLaosan = new ZhangLaosan(new ZhangSan());
		zhangLaosan.findLove();
	}
}
