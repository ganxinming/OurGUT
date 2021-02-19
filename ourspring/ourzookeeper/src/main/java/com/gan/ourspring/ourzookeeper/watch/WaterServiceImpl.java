package com.gan.ourspring.ourzookeeper.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/**
 * @author ganxinming
 * @createDate 2021/2/18
 * @description
 */

@Service
public class WaterServiceImpl implements Watcher {

	private static final Logger logger = LoggerFactory.getLogger(WaterServiceImpl.class);
	@Override
	public void process(WatchedEvent event) {
		logger.info("【Watcher监听事件】={}",event.getState());
		logger.info("【监听路径为】={}",event.getPath());
		logger.info("【监听的类型为】={}",event.getType()); //  三种监听类型： 创建，删除，更新
	}
}
