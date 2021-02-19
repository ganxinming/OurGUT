package com.ourspring.springSecuritys.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author ganxinming
 * @createDate 2021/1/27
 * @description 自定义权限
 */
public class MyGrantedAuthority  implements GrantedAuthority {

	//权限
	private  String authority;

	@Override
	public String getAuthority() {
		return null;
	}

}
