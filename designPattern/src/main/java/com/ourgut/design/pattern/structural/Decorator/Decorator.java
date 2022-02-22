package com.ourgut.design.pattern.structural.Decorator;

/**
 * @author ganxinming
 * @createDate 2022/2/11
 * @description
 *
 * 装饰（Decorator）角色：持有一个构件（Component）对象的实例，并实现一个与抽象构件接口一致的接口。
 */
public abstract class Decorator implements Component{

	protected Component component;

	@Override
	public void operation() {
		if(component != null){
			component.operation();
		}
	}

	public   Decorator(Component component) {
		this.component = component;
	}
}
