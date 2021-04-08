package com.OurGUT.jsonTest.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2021/4/7
 * @description
 */

@NoArgsConstructor
@Data
public class User {


	/**
	 * name : gxm
	 * age : 123
	 * student : true
	 * friend : {"name":"gxm","age":123}
	 */

	private String name;

	private Integer age;

	private Boolean student;

	private FriendDTO friend;

	@NoArgsConstructor
	@Data
	public static class FriendDTO {
		/**
		 * name : gxm
		 * age : 123
		 */

		private String name;

		private Integer age;
	}
}
