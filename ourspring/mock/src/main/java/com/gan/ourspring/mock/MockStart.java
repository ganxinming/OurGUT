package com.gan.ourspring.mock;

import com.gan.ourspring.mock.pojo.User;
import org.junit.Test;
import org.mockito.Mock;


import static org.mockito.Mockito.*;

/**
 * @author ganxinming
 * @createDate 2020/11/25
 * @description
 */
public class MockStart {

	@Mock
	private User user;

	@Test
	public void test1(){
		System.out.println(user.getCard());
		System.out.println(user.getName());
		/**
		 * Mock生成的对象，其实是一个代理对象，不会真正去执行类里面的方法。
		 * 需用使用测试桩Stub(测试桩)来设置Mock对象方法的返回值了
		 */
		User mock = mock(User.class);
		System.out.println(mock);
		// 开始设置测试桩
		// 当get()被调用时，返回"first"
		when(mock.getName()).thenReturn("gg");
		// 当get()被调用时，抛出异常
		System.out.println(mock.getName());

		when(mock.getCard()).thenThrow(new RuntimeException());

		/**
		 * // 使用doReturn语句和when语句一样的效果
		 *  doReturn(1).when(mock).getCard();
		 *  // 输出 1
		 *  System.out.println(mock.getCard());
		 */

		// 第1次调用返回2，第2次返回2，以后再调用返回3
		when(mock.getName()).thenReturn("gg","kk","ll");

		//调动方法，进行设置，返回指定结果
		when(mock.getSet(anyString())).thenReturn("abc");
		System.out.println(mock.getSet("a"));

		when(mock.getIncreate(anyInt(),anyInt())).thenReturn(100);
		System.out.println(mock.getIncreate(1,2));
		//重置mock对象
		reset(mock);

		System.out.println(mock.getCard());
	}
}
