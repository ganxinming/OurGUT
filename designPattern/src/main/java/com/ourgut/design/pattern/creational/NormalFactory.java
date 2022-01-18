package com.ourgut.design.pattern.creational;

import com.ourgut.design.pattern.common.GoICourseFactory;
import com.ourgut.design.pattern.common.ICourse;
import com.ourgut.design.pattern.common.ICourseFactory;
import com.ourgut.design.pattern.common.JavaICourseFactory;

/**
 * @author ganxinming
 * @createDate 2022/1/10
 * @description 工厂模式：其实就是将原来的简单工厂，改成接口实现，产生不同的实例则，生成不同的工厂
 * (则不需要想以前一样，所有的类都有单个工厂去初始化，符合单一原则)
 * 感悟：简单工厂是一个工厂类，而工厂模式就是将需要创建的对象类型，按接口去实现不同的生产类工厂
 * 认为工厂方法是抽象工厂模式的特例的一种，就是只有一个要实现的产品接口
 *
 * 应用场景：
 * Logback中工厂方法模式，
 */
public class NormalFactory {
	public static void main(String[] args) {
		ICourseFactory factory = new GoICourseFactory();
		ICourse course = factory.create();
		course.record();

		factory = new JavaICourseFactory();
		course = factory.create();
		course.record();
	}
}
