package com.ourgut.dubboAdvanceConsumer.filter;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @author ganxinming
 * @createDate 2021/7/1
 * @description
 */
@Slf4j
public class ProviderFilter extends AbstractFilter{

	@Override
	public void setting(String message) {
		log.info("进行provider独有设置:{}",message);
		//设置调用链id
		MDC.put("traceId", UUID.randomUUID().toString());
	}
}
