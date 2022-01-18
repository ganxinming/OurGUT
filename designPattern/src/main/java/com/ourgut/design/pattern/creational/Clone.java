package com.ourgut.design.pattern.creational;

import com.ourgut.design.pattern.common.ConcretePrototype;

/**
 * @author ganxinming
 * @createDate 2022/1/17
 * @description
 * 原型模式：在Java提供的API中，不需要手动创建抽象原型接口，因为Java已经内置了Cloneable抽象原型接口，
 * 自定义的类型只需实现该接口并重写Object.clone()方法即可完成本类的复制。
 * 其实Cloneable是一个空接口。Java之所以提供Cloneable接口，只是为了在运行时通知Java虚拟机可以安全地在该类上使用clone()方法。
 * 而如果该类没有实现 Cloneable接口，则调用clone()方法会抛出 CloneNotSupportedException异常。(所以必须实现接口了)
 *
 * 一般情况下，如果使用clone()方法，则需满足以下条件。
 *
 * （1）对任何对象o，都有o.clone() != o。换言之，克隆对象与原型对象不是同一个对象。
 *
 * （2）对任何对象o，都有o.clone().getClass() == o.getClass()。换言之，复制对象与原对象的类型一样。
 *
 * （3）如果对象o的equals()方法定义恰当，则o.clone().equals(o)应当成立。
 *
 * 我们在设计自定义类的clone()方法时，应当遵守这3个条件。一般来说，这3个条件中的前2个是必需的，第3个是可选的。
 * （1.复制的对象是个新对象2.类对象相同3.equals重写过后，应该相等）
 *
 */
public class Clone {

	public static void main(String[] args) {
		ConcretePrototype concretePrototype=new ConcretePrototype();
		concretePrototype.setAge(18);
		ConcretePrototype clone = concretePrototype.clone();
		/**
		 * 浅克隆，复用对象的引用，本意希望，整个对象复制一份，而不是指向同一个地方的地址
		 */
		clone.getSubject().add("C++");
		System.out.println("clone:"+clone);
		System.out.println("concretePrototype:"+concretePrototype);
		/**
		 * 深克隆,对象类型也能完整复制
		 */
		ConcretePrototype deepClone = concretePrototype.deepClone();
		deepClone.getSubject().add("C#");
		System.out.println("deepClone:"+deepClone);
		System.out.println("concretePrototype:"+concretePrototype);
	}
}
