package com.gan.ourspring.filter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2021/1/10
 * @description
 */
@Data
public class User {

	@NotNull(message = "id不能为空")
	private Long id;

	@NotNull(message = "账户不能为空")
	@Size(min=6,max = 11,message = "长度必须是6-11位")
	private String account;

	@NotNull(message = "密码不能为空")
	@Size(min=6,max = 11,message = "长度必须是6-11位")
	private String password;

	@Email(message = "邮箱格式不正确")
	private String email;
}

