package com.gan.jstorm.spout;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import shade.storm.org.apache.commons.lang.math.RandomUtils;

/**
 * @author ganxinming
 * @createDate 2020/10/24
 * @description
 */
public class WordSpout implements IRichSpout {

	private String[] strs= {"one","two","three","four","five","six"};
	SpoutOutputCollector collector;

	public WordSpout() {
		super();
		System.out.println("WordSpout()****************************");
	}

	/**
	 *打开 stream 流资源，只会执行一次
	 * @param conf
	 * @param context
	 * @param collector
	 */
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		System.out.println("*****************open(Map conf, TopologyContext context, SpoutOutputCollector collector)");
		this.collector=collector;
	}

	@Override
	public void close() {

	}

	@Override
	public void activate() {

	}

	@Override
	public void deactivate() {

	}

	@Override
	public void nextTuple() {
		int index= RandomUtils.nextInt(6);
		collector.emit(new Values(strs[index]));
		System.out.println("***************nextTuple() : "+strs[index]);
	}

	@Override
	public void ack(Object msgId) {

	}

	@Override
	public void fail(Object msgId) {

	}

	/**
	 * 定义发射的字段类型，是第一个要是执行的方法。
	 * @param declarer
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}
