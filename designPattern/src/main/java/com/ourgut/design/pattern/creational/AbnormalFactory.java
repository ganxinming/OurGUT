package com.ourgut.design.pattern.creational;

/**
 * @author ganxinming
 * @createDate 2022/1/10
 * @description
 * 抽象工厂：工厂模式是根据不同的产品去实现不同的工厂类(一类工厂只生产一类产品)，而抽象工厂可以认为生产多类产品，但多类产品都在一个工厂类下
 * 可以认为，工厂模式是只生产某一类的专门工厂，抽象工厂是生产某产品族中所有类的工厂，可以生产很多产品。(在工厂模式上，将产品做了抽象)
 * 了解一下两个名词
 * 产品族：某一个生产者生产的所有产品，这些产品属于一个产品族
 * 产品等级结构：某一类产品的不同生产者生产的产品，属于一个产品等级结构
 *
 * 感悟：给我的感觉，抽象工厂最后又回到了一个工厂，生产许多不同类产品了，和简单工厂做的事一样。
 * 但是呢，抽象工厂只生产一个产品族里的所有东西，将产品也进行了抽象，不同厂家生产不同的产品
 *
 * 实战：抽象工厂模式在Spring源码中的应用
 * public interface BeanFactory {
 * 	String FACTORY_BEAN_PREFIX = "&";
 *
 * 	Object getBean(String name) throws BeansException;
 *
 * 	<T> T getBean(String name, @Nullable Class<T> requiredType) throws BeansException;
 *
 * 	Object getBean(String name, Object... args) throws BeansException;
 *
 * 	<T> T getBean(Class<T> requiredType) throws BeansException;
 *
 * 	<T> T getBean(Class<T> requiredType, Object... args) throws BeansException;
 *
 * 	boolean containsBean(String name);
 *
 * 	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;
 *
 * 	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;
 *
 * 	boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;
 *
 * 	boolean isTypeMatch(String name, @Nullable Class<?> typeToMatch) throws NoSuchBean 	DefinitionException;
 *
 *        @Nullable
 *    Class<?> getType(String name) throws NoSuchBeanDefinitionException;
 * 	String[] getAliases(String name);
 *
 *
 *
 * BeanFactory的子类主要有ClassPathXmlApplicationContext、XmlWebApplicationContext、
 * StaticWebApplicationContext、StaticPortletApplicationContext、GenericApplicationContext
 * 和Static ApplicationContext。在Spring中，DefaultListableBeanFactory实现了所有工厂的公共逻辑。
 * }
 */
public class AbnormalFactory {
	public static void main(String[] args) {
		IFactory factory = new ConcreteFactoryA();
		factory.makeProductA();
		factory.makeProductB();

		factory = new ConcreteFactoryB();
		factory.makeProductA();
		factory.makeProductB();
	}

	//抽象工厂类
	public interface IFactory {
		IProductA makeProductA();

		IProductB makeProductB();
	}

	//产品A抽象
	public interface IProductA {
		void doA();
	}

	//产品B抽象
	public interface IProductB {
		void doB();
	}

	//产品族A的具体产品A
	static class ConcreteProductAWithFamilyA implements IProductA{
		@Override
		public void doA() {
			System.out.println("The ProductA be part of FamilyA");
		}
	}

	//产品族A的具体产品B
	static class ConcreteProductBWithFamilyA implements IProductB{
		@Override
		public void doB() {
			System.out.println("The ProductB be part of FamilyA");
		}
	}

	//产品族B的具体产品A
	static class ConcreteProductAWithFamilyB implements IProductA{
		@Override
		public void doA() {
			System.out.println("The ProductA be part of FamilyB");
		}
	}

	//产品族B的具体产品B
	static class ConcreteProductBWithFamilyB implements IProductB{
		@Override
		public void doB() {
			System.out.println("The ProductB be part of FamilyB");
		}
	}

	//具体工厂类A
	static class ConcreteFactoryA implements IFactory{
		@Override
		public IProductA makeProductA() {
			return new ConcreteProductAWithFamilyA();
		}

		@Override
		public IProductB makeProductB() {
			return new ConcreteProductBWithFamilyA();
		}
	}

	//具体工厂类B
	static class ConcreteFactoryB implements IFactory{
		@Override
		public IProductA makeProductA() {
			return new ConcreteProductAWithFamilyB();
		}

		@Override
		public IProductB makeProductB() {
			return new ConcreteProductBWithFamilyB();
		}
	}

}
