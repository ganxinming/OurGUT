package com.ourspring.springSecuritys.config;

import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourspring.springSecuritys.service.jpa.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.accept.ContentNegotiationStrategy;

/**
 * @author ganxinming
 * @createDate 2021/1/26
 * @description
 * Spring Security 本身是在 Spring 容器中是作为作为一个 Filter
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	UserService userService;

	protected WebSecurityConfig() {
		super();
	}

	protected WebSecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		//自定义内存中的用户
//		auth.inMemoryAuthentication().withUser("shuen").password("123").roles("admin");
		//设置默认service
//		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
		//设置自定义service
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 构造authenticationManager()，核心
	 * @return
	 * @throws Exception
	 */
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}


	@Override
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	@Override
	public void init(WebSecurity web) throws Exception {
		super.init(web);
	}

	/**
	 * 用来配置静态资源处理，当然也可以放在下面的config中，但是这种方便点，不用走过滤链
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**/*.html","/resources/**/*.js");
	}

	/**
	 * 核心配置，设置拦截器，
	 * @param http
	 * @throws Exception
	 *   这里说明下，匹配 URL 的规则是从上到下，按照顺序匹配的，如果前面的已经匹配了，则后面的不再进行匹配；
	 * 比较常见的几种认证方式
	 * HTTP BASIC authentication headers：基于IETF RFC 标准。
	 * HTTP Digest authentication headers：基于IETF RFC 标准。
	 * HTTP X.509 client certificate exchange：基于IETF RFC 标准。
	 * LDAP：跨平台身份验证。
	 * Form-based authentication：基于表单的身份验证。
	 * Run-as authentication：用户用户临时以某一个身份登录。
	 * OpenID authentication：去中心化认证。
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// 表示允许使用HttpServletRequest限制访问
				.authorizeRequests()
//				.antMatchers("/","/login").permitAll()
				//对静态资源放开
				.antMatchers("/resources/**", "/signup", "/about").permitAll()
				//特定权限访问，注意在数据库中会补全字段 变成ROLE_ADMIN
				.antMatchers("/admin/**").hasRole("ADMIN")
				// 对于这样多角色判断的，使用 access 连接
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				// 对任何请求，都必须认证后才能访问
				.anyRequest().authenticated()
				//自定义鉴权处理器
//				.accessDecisionManager()

				.and()
				// 启动基于表单验证，注意！！！表单登录则传入的必须是key，value形式，而不能是json
				.formLogin()
				//配置登录页面，没有则使用spring默认的
				.loginPage("/login123")
				//请求用来提交登录数据,如果上下两个路径没有指定，默认是一样，通过get/post指定
				.loginProcessingUrl("/doLogin")
				//可以设置参数名，感觉没什么屌用
				.usernameParameter("name")
				.passwordParameter("passwd")
				//成功后来到的地址，但是默认会重定向回原始没登录前的地址
				.defaultSuccessUrl("/index",false)
				//成功后来到的地址，不管原先什么地址，就统一跳转至/index
				.successForwardUrl("/index")
				//登录失败页面
				.failureForwardUrl("/error")
				.successHandler((req, resp, authentication) -> {
					Object principal = authentication.getPrincipal();
					resp.setContentType("application/json;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.write(new ObjectMapper().writeValueAsString(principal));
					out.flush();
					out.close();
				})
				.permitAll()

				.and()
				/**
				 * 实现记住我的功能
				 * 原理：cookie保存一个rememberMe: 加密(key+用户名+密码) 值
				 * 下次请求时，cookie带上这个字段，则自动被取出比对是否登录过
				 * 风险很大，存在多用户同时登录，可以使用持久化令牌避免(原理：每次登录产生新的令牌，原令牌失效)
				 */
				.rememberMe()
				//为设置一个key，如不设置成固定的，则机器重启后会自动生成新的，则无法识别
				.key("javaToken")
				.and()
				/**
				 * 默认抵御csrf攻击，可以关闭
				 */
				.csrf().disable()
				/** 访问/logout，自动完成注销，主要做了下面几件事
				 * 1、销毁对应的会话 session ；
				 * 2、清除任何已经配置的 记住我 身份验证；
				 * 3、清除 SecurityContextHolder ；
				 * 4、重定向到 /login?logout ；
				 */
//				.logout().permitAll()
				//可以自定义logout
				.logout()
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/my/index")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
				//登出清除一些信息
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies()
				.permitAll()
				.and()

				/**
				 *  表示配置最大会话数为 1,则后续登录进来的会踢掉前一个
				 */
				.sessionManagement()
				.maximumSessions(1)
				/**
				 * 开启这个，则保证当前用户继续登录，其他用户不能登录，需要注入下面HttpSessionEventPublisher
				 */
				.maxSessionsPreventsLogin(true);
	}

	@Bean
	HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
