package com.ourspring.jvm.reference;

/**
 * @author ganxinming
 * @createDate 2021/8/22
 * @description 进行垃圾回收，执行finalize方法
 */
public class GanReference {

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("GanReference 进行了finalize");
	}
}
