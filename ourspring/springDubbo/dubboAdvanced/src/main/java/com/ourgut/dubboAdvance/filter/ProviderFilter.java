package com.ourgut.dubboAdvance.filter;

import cn.hutool.json.JSONUtil;
import com.springDubbo.dubboService.entity.Transport;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.MDC;

/**
 * @author ganxinming
 * @createDate 2021/7/1
 * @description 对于RpcContext，A->B->C，在C处默认得不到A的参数，需要在B处重新放入A的参数，所以做个过滤器尤为重要
 *
 * A invokes B, then B invokes C. On service B, RpcContext saves invocation info from A to B before B
 */
@Slf4j
public class ProviderFilter extends AbstractFilter{

	@Override
	public void setting(Transport transport) {
		log.info("进行provider独有设置:{}",transport.toString());
		//需要重新设置
		RpcContext.getContext().setAttachment("transport", JSONUtil.toJsonStr(transport));
		//设置调用链id,可以打印在日志上，需要在日志上设置，详细见日志模块
		MDC.put("traceId", transport.getTraceId());
	}
}
