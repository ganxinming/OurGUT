package com.ourspring.springdubboconsumer.invoker;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springDubbo.dubboService.TestService;
import org.apache.dubbo.rpc.RpcContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ganxinming
 * @createDate 2021/3/17
 * @description
 */
@RestController
public class TestInvokeController {

	@Autowired
	TestService testService;

	@RequestMapping("/invokeServiceImpl")
	public String invokeServiceImpl(){
		String s = testService.startInvoke();
		return s;
	}

//	@RequestMapping("/setAttachment")
//	public String setAttachment(){
//		RpcContext.getContext().setAttachment("index", "1"); // 隐式传参，后面的远程调用都会隐式将这些参数发送到服务器端，类似cookie，用于框架集成，不建议常规业务使用
//		return "";
//	}

}
