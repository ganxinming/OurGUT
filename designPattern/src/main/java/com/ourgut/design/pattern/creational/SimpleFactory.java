package com.ourgut.design.pattern.creational;

import com.ourgut.design.pattern.common.GoCourse;
import com.ourgut.design.pattern.common.ICourse;
import com.ourgut.design.pattern.common.JavaCourse;

/**
 * @author ganxinming
 * @createDate 2022/1/8
 * @description 简单工厂：一般是通过传入名称，或者class类对象进行创建。（他是一个具体的工厂类，只做生成类）
 *  工厂方法模式主要适用于以下应用场景。
 *
 * （1）创建对象需要大量重复的代码。
 *
 * （2）客户端（应用层）不依赖产品类实例如何被创建、实现等细节。
 *
 * （3）一个类通过其子类来指定创建哪个对象。
 *
 *
 * 简单工厂模式在Logback源码中的应用#
 * 在大家经常使用的Logback中，可以看到LoggerFactory中有多个重载的方法getLogger()。
 *
 *
 * public static Logger getLogger(String name) {
 *     ILoggerFactory iLoggerFactory = getILoggerFactory();
 *     return iLoggerFactory.getLogger(name);
 * }
 *
 * public static Logger getLogger(Class clazz) {
 *     return getLogger(clazz.getName());
 * }
 */
public class SimpleFactory {
	public static void main(String[] args) {
		SimpleFactory factory = new SimpleFactory();
		ICourse java = factory.createByName("java");
		ICourse byclass = factory.createByclass(GoCourse.class);
		System.out.println(java.hashCode()+byclass.hashCode());
	}

	/**
	 * 通过名称挺好，但是每次新增加一种类，就需要改逻辑，有没有只需要改变传入参数，而里面的逻辑不用改也能创建呢，当然可以通过反射
	 * @param name
	 * @return
	 */
	public ICourse createByName(String name){
		if("java".equals(name)){
			return new JavaCourse();
		}else if("go".equals(name)){
			return new GoCourse();
		}else {
			return null;
		}
	}

	/**
	 * 通过类去创建
	 */

	public ICourse createByclass(Class<? extends ICourse> clazz){
		try {
			if(clazz!=null){
				return clazz.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
