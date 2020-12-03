package com.ourspring.hbase.config;

/**
 * @author ganxinming
 * @createDate 2020/12/1
 * @description
 */

import java.io.Serializable;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class HbaseConfig implements Serializable {

	@Value("${hbase.zookeeper.xxxxx.clientPort}")
	private String hbaseZkPort;

	@Value("${hbase.zookeeper.xxxxx.quorum}")
	private String hbaseZkQuorum;

	@Value("${hbase.zookeeper.xxxxx.parent}")
	private String hbaseZkNodeParent;

}

