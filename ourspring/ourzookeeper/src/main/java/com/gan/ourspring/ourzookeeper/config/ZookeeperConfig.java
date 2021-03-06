package com.gan.ourspring.ourzookeeper.config;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ganxinming
 * @createDate 2021/2/18
 * @description
 */
@Configuration
public class ZookeeperConfig {

	private static final Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);

	@Value("${zookeeper.address}")
	private String connectString;

	@Value("${zookeeper.timeout}")
	private  int timeout;

	@Bean(name = "zkClient")
	public ZooKeeper zkClient() {
		ZooKeeper zooKeeper = null;
		try {
			CountDownLatch countDownLatch = new CountDownLatch(1);
			zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
				@Override
				public void process(WatchedEvent watchedEvent) {
					if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
						//如果收到了服务端的响应事件,连接成功
						countDownLatch.countDown();

					}
				}
			});
			countDownLatch.await();
		}catch (Exception ex)
		{
			logger.error("初始化ZooKeeper连接异常....】={}",ex);
		}
		return zooKeeper;
	}

}
