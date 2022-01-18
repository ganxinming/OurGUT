package com.ourgut.design.pattern.creational;

import com.ourgut.design.pattern.common.Course;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description
 * 建造者模式：通过将复杂的创建过程封装起来，分成多个不同的步骤。
 * 一般情况下，我们更习惯使用静态内部类的方式实现建造者模式，即一个产品类内部自动带有一个具体建造者，
 * 由它负责该产品的组装创建，不再需要Builder和Director，这样，产品表示与创建之间的联系更加紧密，结构更加紧凑，
 * 同时使得建造者模式的形式更加简洁。
 *
 * 作用：灵活构造对象，不用set，和构造方法
 *
 *
 * 传统build方式：
 * 如上图所示，builder模式有4个角色。
 * Product: 最终要生成的对象，例如 Computer实例。
 * Builder： 构建者的抽象基类（有时会使用接口代替）。其定义了构建Product的抽象步骤，其实体类需要实现这些步骤。
 * 其会包含一个用来返回最终产品的方法Product getProduct()。
 * ConcreteBuilder: Builder的实现类。
 * Director: 决定如何构建最终产品的算法. 其会包含一个负责组装的方法void Construct(Builder builder)，
 * 在这个方法中通过调用builder的方法，就可以设置builder，等设置完成后，就可以通过builder的 getProduct() 方法获得最终的产品。
 *
 * 其实传统的方式还是挺好的，通过变化不同的算法从而可以指导构建出不同的构建者Builder，就是太麻烦了，但是能统一管理创建。
 *
 * 使用场景：java的build创建，大部分sql创建器
 */
public class Builder {
	public static void main(String[] args) {
		Course course = new Course.Builder()
				.addName("设计模式")
				.addPpt("【PPT课件】")
				.addVideo("【录播视频】")
				.builder();

		System.out.println(course);
	}
}
