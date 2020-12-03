package com.ourspring.hbase.config;

/**
 * @author ganxinming
 * @createDate 2020/12/1
 * @description
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: risk-jstorm
 * @description: ${description}
 * @author: maqiang
 * @create: 2019/5/23
 */
@Component
public class HbaseManager {
	private static Logger LOG = LoggerFactory.getLogger(HbaseManager.class);

	@Autowired
	private HbaseConfig hbaseConfig;

	@Bean(value = "hbaseClient")
	public Hbase getHbase() {
		try {
			Configuration configuration = HBaseConfiguration.create();
			configuration.set("hbase.zookeeper.property.clientPort", hbaseConfig.getHbaseZkPort());
			configuration.set("hbase.zookeeper.quorum", hbaseConfig.getHbaseZkQuorum());
			configuration.set("zookeeper.znode.parent", hbaseConfig.getHbaseZkNodeParent());
			return new Hbase(configuration);
		} catch (Exception e) {
			LOG.error(" init Hbase error , ", e);
		}
		return null;
	}
}

