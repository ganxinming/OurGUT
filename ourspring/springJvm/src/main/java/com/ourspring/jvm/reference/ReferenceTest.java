package com.ourspring.jvm.reference;

/**
 * @author ganxinming
 * @createDate 2021/8/22
 * @description
 */
public class ReferenceTest {
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
