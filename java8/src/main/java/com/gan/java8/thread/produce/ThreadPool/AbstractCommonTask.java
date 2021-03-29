package com.gan.java8.thread.produce.ThreadPool;

import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * @author ganxinming
 * @createDate 2021/3/29
 * @description
 */
@Slf4j
public abstract class AbstractCommonTask implements CommonTask{

	private static Logger LOG = (Logger) LoggerFactory.getLogger(AbstractCommonTask.class);
	private final static ThreadLocal<Long> TIME_CONSUMER_WATCHER = ThreadLocal.withInitial(System::currentTimeMillis);

	@Override
	public void beforeExecute() {
		TIME_CONSUMER_WATCHER.set(System.currentTimeMillis());
	}

	@Override
	public void afterExecute() {
		LOG.info("CommonTask is perfection,consume "+(System.currentTimeMillis()-TIME_CONSUMER_WATCHER.get()));
		TIME_CONSUMER_WATCHER.remove();
	}

	@Override
	public abstract void doExecute() ;

}

