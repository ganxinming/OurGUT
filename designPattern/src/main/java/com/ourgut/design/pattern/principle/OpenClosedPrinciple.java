package com.ourgut.design.pattern.principle;

/**
 * @author ganxinming
 * @createDate 2021/12/31
 * @description 开闭原则：应该对扩展开放，对修改关闭，例如版本更新，我们尽可能不修改源代码，但是可以增加新功能。
 * 感悟：如果不想影响原类，可以扩展一个类去继承原类
 */
public class OpenClosedPrinciple {

	/**
	 * 现在已有一个类继承接口，并实现了获取价格的方法
	 */
	public class JavaCourse implements ICourse{
		private Integer Id;
		private String name;
		private Double price;
		public JavaCourse(Integer id, String name, Double price) {
			this.Id = id;
			this.name = name;
			this.price = price;
		}
		@Override
		public Integer getId() {
			return this.Id;
		}
		@Override
		public String getName() {
			return this.name;
		}
		@Override
		public Double getPrice() {
			return this.price;
		}
	}

	/**
	 * 但是现在，某种场景下，这个类的价格需要打折，避免在原来的类上修改，新增一个类去继承修改
	 * (其实只是想描述这种思想，能不改就不改，但是我个人觉得，如果类引用的地方就这里有，是可以直接在原类上改的，如果突然新增一个类
	 * 后面的人可能就看不懂，也不清楚要用哪个，就算要改也不能修改继承父类的非抽象方法，这里例子是错误。正确的应该是新增一个方法)
	 */
	public class JavaDiscountCourse extends JavaCourse {
		public JavaDiscountCourse(Integer id, String name, Double price) {
			super(id, name, price);
		}
		public Double getOriginPrice(){
			return super.getPrice();
		}
		@Override
		public Double getPrice(){
			return super.getPrice() * 0.61;
		}
	}
}
