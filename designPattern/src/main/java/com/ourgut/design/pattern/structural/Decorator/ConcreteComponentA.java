package com.ourgut.design.pattern.structural.Decorator;

/**
 * @author ganxinming
 * @createDate 2022/2/11
 * @description
 *
 * ConcreteDecorator：具体的装饰器实现者，负责实现装饰器角色定义的功能。
 */
public class ConcreteComponentA extends Decorator{

	@Override
	public void operation() {
		super.operation();
		System.out.println("where a=a");
	}

	public ConcreteComponentA(Component component) {
		super(component);
	}
}
