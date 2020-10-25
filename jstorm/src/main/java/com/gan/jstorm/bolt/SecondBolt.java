package com.gan.jstorm.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.gan.jstorm.Ourjstorm;

/**
 * @author ganxinming
 * @createDate 2020/10/23
 * @description
 */
public class SecondBolt implements IRichBolt {

	private OutputCollector outputCollector;

	@Override
	public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
		this.outputCollector=outputCollector;
	}

	@Override
	public void execute(Tuple tuple) {
		String key = (String) tuple.getValueByField("key");
		if (key == null){
			return;
		}
		Object message = tuple.getValueByField("message");
		//转成对象进行处理
		if (message instanceof Ourjstorm){
			try {
				Ourjstorm ourjstorm= (Ourjstorm) message;
				System.out.println("开始处理逻辑"+ourjstorm);
				/**
				 * 进行逻辑处理
				 */
				//ack之前已经实现
				outputCollector.ack(tuple);
				//进行处理逻辑后发送下一个bolt，如果没有下一个bolt，则无需发送了
				outputCollector.emit(new Values(ourjstorm.getId(),ourjstorm));
			} catch (Exception e) {
				outputCollector.fail(tuple);
			}
		}
	}

	@Override
	public void cleanup() {

	}

	/**
	 * 定义下一个values对于的key值
	 * @param outputFieldsDeclarer
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields("key","nextMessage"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}
