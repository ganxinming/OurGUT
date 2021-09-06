package com.ourgut.esjob.job;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/9/3
 * @description
 */
@Component
@Slf4j
public class FixedPrintTask {
	private int i;

	@Scheduled(cron = "*/5 * * * * ?")
	public void execute() {
		log.info("thread id:{},FixedPrintTask execute times:{}", Thread.currentThread().getId(), ++i);
	}

}
