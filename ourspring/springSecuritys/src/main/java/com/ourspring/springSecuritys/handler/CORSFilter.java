package com.ourspring.springSecuritys.handler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ganxinming
 * @createDate 2021/8/8
 * @description 统一解决跨域
 * 这里没有应用进去，可以设置在springSecurity中
 */
public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String origin = request.getHeader("Origin");
		response.setHeader("Access-Control-Allow-Origin", origin);//该字段是必须的，* 表示接受任意域名的请求，还可以指定域名
		response.setHeader("Access-Control-Allow-Headers", "Content-Type,dsf-token");//该字段可选，里面可以获取Cache-Control、Content-Type、Expires等，如果想要拿到其他字段，就可以在这个字段中指定。比如图中指定的GUAZISSO
		response.setHeader("Access-Control-Allow-Credentials", "true");//该字段可选，是个布尔值，表示是否可以携带cookie，(注意：如果Access-Control-Allow-Origin字段设置*，此字段设为true无效)
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD,PUT");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}

