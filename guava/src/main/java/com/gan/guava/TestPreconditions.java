package com.gan.guava;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2020/10/25
 * @description 先决条件：提前进行判断是否符合(前置判断)
 */
public class TestPreconditions {

	/**
	 * 每个判断方法都有三个多态方法：
	 * 没有额外参数：抛出的异常中没有错误消息；
	 * 有一个Object对象作为额外参数：抛出的异常使用Object.toString() 作为错误消息；
	 * 有一个String对象作为额外参数，并且有一组任意数量的附加Object对象：这个变种处理异常消息的方式有点类似printf，但考虑GWT的兼容性和效率，只支持%s指示符。例如：
	 * checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
	 */
	@Test
	public void TestPrecondition(){
		List<Integer> list= Lists.newArrayList(2);
//		List<Integer> list= null;
		try {
			//表达式进行参数检查判断
			Preconditions.checkArgument(list.get(0)>1,"参数不满足条件:%s",list);
			//对象提前判空
			Preconditions.checkNotNull(list,"空指针异常:%s",list);
			//和参数检测差不多
			Preconditions.checkState(list.get(0) > 1,"对象状态异常");
			//判断下标是否越界,判断0<=index<size
			Preconditions.checkElementIndex(1,list.size(),"下标越界");
			//判断位置是否越界，判断0<=index<=size
			Preconditions.checkPositionIndex(2,list.size(),"位置越界");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
