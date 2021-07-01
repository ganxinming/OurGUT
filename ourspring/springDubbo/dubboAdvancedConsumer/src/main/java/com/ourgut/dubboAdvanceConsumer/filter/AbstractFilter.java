package com.ourgut.dubboAdvanceConsumer.filter;

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
 */
public abstract class AbstractFilter implements Filter {


	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		//dubbo隐式透传,作为consumer，可以认为是调用的源头
		Transport transport=new Transport();
		transport.setMessage("消费者带来的消息");
		transport.setTraceId("消费者的链路id");
		RpcContext.getContext().setAttachment("transport", JSONUtil.toJsonStr(transport));
		RpcContext context = RpcContext.getContext();
		setting(context.getAttachments().toString());
		return invoker.invoke(invocation);
	}

	public abstract void setting(String message);
}
