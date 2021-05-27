package com.test.thread;

/**
 * @author ganxinming
 * @createDate 2021/4/13
 * @description
 */
public class TestCoreNum {
	public static void main(String[] args) {
		int CORE_NUM = Runtime.getRuntime().availableProcessors();
		System.out.println(CORE_NUM);
		System.out.println("test1");
	}
}
