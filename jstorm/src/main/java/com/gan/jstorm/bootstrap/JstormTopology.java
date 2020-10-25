package com.gan.jstorm.bootstrap;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import com.gan.jstorm.bolt.FirstBolt;
import com.gan.jstorm.spout.FirstSpout;

/**
 * @author ganxinming
 * @createDate 2020/10/23
 * @description
 */
public class JstormTopology {

	public static void main(String[] args) {

		TopologyBuilder builder = new TopologyBuilder();
		//设置spout1,默认一个线程一个任务,这里4个任务，两个线程执行，一个线程执行两个任务
		builder.setSpout("firstSpout",new FirstSpout("steam1"),2).setNumTasks(4);

		//fieldsGrouping中streamId为接收的上一个的spout或bolt的id，也是靠这个来形成链路
		//通过fieldsGrouping指定Fields，保证相同的Fields被同一个线程处理
		builder.setBolt("firstBolt",new FirstBolt(),2)
				.fieldsGrouping("firstSpout","steam1",new Fields("key"));

//		builder.setSpout("secondSpout",new FirstSpout("steam2"),2).setNumTasks(4);
//
//		//fieldsGrouping中streamId为接收的上一个的spout或bolt的id，也是靠这个来形成链路
//		//通过fieldsGrouping指定Fields，保证相同的Fields被同一个线程处理
//		builder.setBolt("secondBolt",new FirstBolt(),2)
//				.fieldsGrouping("secondSpout","steam2",new Fields("key"));
//

		Config config=new Config();
		//线上集群模式
//		try {
		//定义两个进程执行
//			config.setNumWorkers(2);
//			config.setNumAckers(1);
//			config.setMessageTimeoutSecs(15);
//			StormSubmitter.submitTopology("JstormTopology", config, builder.createTopology());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//本地模式
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("JstormTopology", config, builder.createTopology());
	}

}
