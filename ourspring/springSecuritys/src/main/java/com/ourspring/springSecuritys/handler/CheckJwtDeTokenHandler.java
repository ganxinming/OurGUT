package com.ourspring.springSecuritys.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourspring.springSecuritys.controller.UserController;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author ganxinming
 * @createDate 2021/8/8
 * @description
 */
@Component
public class CheckJwtDeTokenHandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//在本次实例看不到这种情况，因为没有前后端分离
		//如果使用ajax进行前后分离，对于每次请求，都会发送一个OPTIONS的预检请求，需要放行
		/**
		 * 其实在正式跨域之前,浏览器会根据需要发起一次预检(也就是option请求)，用来让服务端返回允许的方法(如get、post)
		 * 简单请求(GET、POST、HEAD)浏览器不会预检，而非简单请求（json结构参数）会预检
		 * 当然这种方法不太好，最好是另外做一个corsFilter过滤
		 */
		if("OPTIONS".equalsIgnoreCase(request.getMethod())){
			return true;
		}

		String token=request.getParameter("token");

		//一般token在后端生成后返回，被前端放在header中，这里为了方便，从parameter中取
//		String token=request.getHeader("token");
		if (StringUtils.isNotBlank(token)){
			//解析token
			UserController.deToken(token);
		}
		else{
			System.out.println("没有携带token，请先登录");
			doResponse(request,response);
		}
		return true;
	}

	private void doResponse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		String string = new ObjectMapper().writeValueAsString("可传对象");
		writer.print(string);
		writer.flush();
		writer.close();
	}
}
