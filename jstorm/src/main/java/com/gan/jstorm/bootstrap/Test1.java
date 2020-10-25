package com.gan.jstorm.bootstrap;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import com.gan.jstorm.bolt.CountBolt;
import com.gan.jstorm.spout.WordSpout;

/**
 * @author ganxinming
 * @createDate 2020/10/24
 * @description
 */
public class Test1 {
	public static void main(String[] args) {
		TopologyBuilder builder = new TopologyBuilder();

		//1 指定任务的spout组件
		builder.setSpout("1",new WordSpout());

		//2 指定任务的第一个bolt组件
		builder.setBolt("2",new CountBolt()).shuffleGrouping("1");
		//1、本地模式
		Config config = new Config();
		LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("MyWordCount",config,builder.createTopology());

	}
}
