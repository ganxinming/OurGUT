package com.ourgut.dubboAdvance.filter;

import cn.hutool.json.JSONUtil;
import com.springDubbo.dubboService.entity.Transport;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author ganxinming
 * @createDate 2021/7/1
 * @description
 * 1.配置Filter实现类
 * 2.配置SPI,com.alibaba.dubbo.rpc.Filter
 * 3.配置filter到xml
 */

public abstract class AbstractFilter implements Filter {


	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		//dubbo隐式透传
		String  transport= RpcContext.getContext().getAttachment("transport");
		Transport transport1 = JSONUtil.toBean(transport, Transport.class);
		setting(transport1);
		return invoker.invoke(invocation);
	}

	public abstract void setting(Transport transport);
}
