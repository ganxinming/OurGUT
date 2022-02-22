package com.ourgut.design.pattern.structural.Decorator;

/**
 * @author ganxinming
 * @createDate 2022/2/11
 * @description
 * 动态的为一个对象增加新的功能
 * 装饰模式是一种用于代替继承的技术。无需通过继承增加子类就能扩展对象的新功能。
 * 使用对象的关联关系代替继承关系，更加灵活，同时避免类型体系的快速膨胀。
 *
 * 装饰器模式有如下结构：
 *
 * • Component：抽象组件，定义了一组抽象的接口，规定这个被装饰类有哪些功能。
 *
 * • ConcreteComponent： 实现这个抽象组件的所有功能。
 *
 * • Decorator：装饰器角色， 它持有一个Component 对象实例的引用。
 *
 * • ConcreteDecorator：具体的装饰器实现者，负责实现装饰器角色定义的功能。
 *
 * 原理：通过一个抽象装饰者，内部维持一个组件的引用，然后子类实现装饰者，可以自定义逻辑了，因为可以自由使用内部引用了
 *
 */
public class Test {

	public static void main(String[] args) {
		/**
		 * 创建基础组件
		 */
		ConcreteComponent concreteComponent=new ConcreteComponent();
		/**
		 * 装饰基础组件
		 */
		ConcreteComponentA concreteComponentA=new ConcreteComponentA(concreteComponent);
		/**
		 * 进一步装饰组件
		 */
		ConcreteComponentB concreteComponentB=new ConcreteComponentB(concreteComponentA);
		concreteComponentB.operation();
	}
}
