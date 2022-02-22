package com.test.thread;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.gan.java8.thread.pojo.User;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2021/12/8
 * @description
 */
public class TestSteam {

	@Test
	public void testSort(){

		List<User> userList= Lists.newArrayList(User.builder().id(1).name("g").build()
				,User.builder().id(3).name("3").build()
				,User.builder().id(2).name("2").build(),User.builder().id(2).name("2").build()
				,User.builder().id(9).name("2").build()
				,User.builder().id(10).name("2").build()
				,User.builder().id(0).name("2").build());
		 userList.stream().sorted(Comparator.comparing(User::getId)).forEach(x->{
			 System.out.println(x.getId());
		 });
		System.out.println("---");
		for(User user:userList){
			System.out.println(user.getId());
		}
	}
}
