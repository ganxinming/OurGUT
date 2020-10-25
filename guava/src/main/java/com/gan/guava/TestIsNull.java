package com.gan.guava;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2020/10/25
 * @description 空值判断
 */
public class TestIsNull {

	@Test
	public void testOptional(){
//		List list= Lists.newArrayList(1,2,3,null);
		List list= null;
		Optional<List> listOptional = Optional.fromNullable(list);
		//如果Optional包含非null的引用,返回true
		System.out.println(listOptional.isPresent());
		//获取list对象,null将触发异常
		try {
			System.out.println(listOptional.get());
		} catch (Exception e) {
			System.out.println("空指针异常");
		}
		//获取list对象,包含的引用缺失，返回null，不触发异常
		System.out.println(listOptional.orNull());
		//获取list对象,返回默认值
		System.out.println(listOptional.or(new ArrayList()));

	}
}
