package com.ourgut.design.pattern.common;

/**
 * @author ganxinming
 * @createDate 2022/1/18
 * @description
 */
public class ZhangSan implements IPerson {

	@Override
	public void findLove() {
		System.out.println("儿子张三提出要求");
	}

	@Override
	public void startLove() {
		System.out.println("张三开始恋爱");
	}

}
