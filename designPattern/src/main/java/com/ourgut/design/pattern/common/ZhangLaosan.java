package com.ourgut.design.pattern.common;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description
 */
public class ZhangLaosan implements IPerson {

	private ZhangSan zhangsan;

	public ZhangLaosan(ZhangSan zhangsan) {
		this.zhangsan = zhangsan;
	}

	@Override
	public void findLove() {
		System.out.println("张老三开始物色");
		zhangsan.findLove();
		System.out.println("开始交往");
	}

	@Override
	public void startLove() {
		System.out.println("张老三开始恋爱");
	}

}

