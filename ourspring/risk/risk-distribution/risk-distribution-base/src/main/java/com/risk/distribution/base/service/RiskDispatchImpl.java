package com.risk.distribution.base.service;

import com.risk.distribution.common.RiskDispatch;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/7/2
 * @description
 */
@Service
@Slf4j
public class RiskDispatchImpl implements RiskDispatch {

	@Override
	public void executeDispatch() {
		log.info("执行介质分发");
	}
}