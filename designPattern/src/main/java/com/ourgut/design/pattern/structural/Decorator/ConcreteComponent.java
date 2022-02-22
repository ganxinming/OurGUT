package com.ourgut.design.pattern.structural.Decorator;

/**
 * @author ganxinming
 * @createDate 2022/2/11
 * @description
 * 具体构件（Concrete Component）角色：定义一个将要接收附加责任的类。
 */
public class ConcreteComponent implements Component{
	@Override
	public void operation() {
		System.out.println("select * from A");
	}
}
