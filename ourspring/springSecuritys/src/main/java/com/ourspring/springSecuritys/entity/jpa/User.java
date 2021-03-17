package com.ourspring.springSecuritys.entity.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ganxinming
 * @createDate 2021/2/1
 * @description
 * 用户类实体实现UserDetails，可以添加自己需要的实体属性，为UserDetailsService提供服务
 *  UserDetails 默认提供了：
 * 用户的权限集， 默认需要添加 ROLE_ 前缀
 * 用户的加密后的密码， 不加密会使用 {noop} 前缀
 * 应用内唯一的用户名
 * 账户是否过期
 * 账户是否锁定
 * 凭证是否过期
 * 用户是否可用
 */
@Data
@Entity(name = "t_user")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	private List<Role> roles;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}


}
