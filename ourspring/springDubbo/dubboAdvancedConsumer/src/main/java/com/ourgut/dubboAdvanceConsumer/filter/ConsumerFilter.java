package com.ourgut.dubboAdvanceConsumer.filter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ganxinming
 * @createDate 2021/7/1
 * @description
 */
@Slf4j
public class ConsumerFilter extends AbstractFilter{

	@Override
	public void setting(String message) {
		log.info("进行consumer独有设置:{}",message);
	}
}
