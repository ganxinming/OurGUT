package com.risk.core.base.service;

import com.risk.core.common.service.RiskInvoke;
import com.risk.distribution.common.RiskDispatch;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/7/2
 * @description
 */
@Service
@Slf4j
public class RiskInvokeImpl implements RiskInvoke {

	@Autowired RiskDispatch riskDispatch;

	@Override
	public void riskExecute() {
		riskDispatch.executeDispatch();
		log.info("风控执行");
	}
}
