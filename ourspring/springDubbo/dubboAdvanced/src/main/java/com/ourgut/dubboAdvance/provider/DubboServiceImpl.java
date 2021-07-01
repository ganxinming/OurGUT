package com.ourgut.dubboAdvance.provider;

import com.springDubbo.dubboService.provider.DubboService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/6/30
 * @description
 */
@Slf4j
@Service("dubboService")
public class DubboServiceImpl implements DubboService {
	@Override
	public void serviceInvoke() {
		log.info("调用服务端 DubboServiceImpl");
	}
}
