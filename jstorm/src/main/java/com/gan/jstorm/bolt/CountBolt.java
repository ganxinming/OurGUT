package com.gan.jstorm.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

/**
 * @author ganxinming
 * @createDate 2020/10/24
 * @description
 */
public class CountBolt implements IRichBolt {
	public CountBolt() {
		System.out.println("CountBolt:**********************************");
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

	}

	@Override
	public void execute(Tuple input) {
		System.out.println("**********execute(Tuple input)");
		String word=input.getString(0);
		Object word1 = input.getValueByField("word");
		System.out.println("value:"+word1);
	}

	@Override
	public void cleanup() {

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}
