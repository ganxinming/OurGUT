package com.ourspring.jvm.reference;

/**
 * @author ganxinming
 * @createDate 2021/8/22
 * @description
 */
public class ReferenceTest {
	/**
	 * 因为是静态属性，所以创建过程发生gc也不会释放
	 */
	public static void createOOM(){
		String[] array = new String[10240 * 10];
		for(int i = 0; i < 1024 * 10; i++) {
			for(int j = 'a'; j <= 'z'; j++) {
				array[i] += (char)j;
			}
		}
	}

//	@Override
//	protected void finalize() throws Throwable {
//		super.finalize();
//		System.out.println("ReferenceTest 进行finalize");
//	}
}
