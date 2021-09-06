package com.ourgut.spring.sqlite.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ganxinming
 * @createDate 2021/8/27
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {
	/**
	 * 用户编号
	 */
	private Integer id;
	/**
	 * 账号
	 */
	private String username;
	/**
	 * 密码（明文）
	 *
	 * ps：生产环境下，千万不要明文噢
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
