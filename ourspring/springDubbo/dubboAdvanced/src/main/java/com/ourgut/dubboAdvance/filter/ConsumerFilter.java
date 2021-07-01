package com.ourgut.dubboAdvance.filter;

import com.springDubbo.dubboService.entity.Transport;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ganxinming
 * @createDate 2021/7/1
 * @description
 */
@Slf4j
public class ConsumerFilter extends AbstractFilter{


	@Override
	public void setting(Transport transport) {
		log.info("进行consumer独有设置:{}",transport.toString());
	}
}
