package com.gan.jstorm.spout;

import java.time.Duration;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gan.jstorm.Ourjstorm;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import shade.storm.com.google.common.base.Strings;

/**
 * @author ganxinming
 * @createDate 2020/10/22
 * @description
 */
public class FirstSpout extends BaseRichSpout {

	private SpoutOutputCollector outputCollector;

	private String declareName;

	private KafkaConsumer kafkaConsumer;
	static int i=0;
	public FirstSpout(String declareName) {
		this.declareName=declareName;
	}

	@Override
	public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
		this.outputCollector=spoutOutputCollector;
	}

	/**
	 * 循环发送消息
	 */
	@Override
	public void nextTuple() {
//		//使用byte[]便于对象传输
//		ConsumerRecords<String,byte[] > consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(100));
//		if (consumerRecords.isEmpty() || consumerRecords.count() <=0){
//			System.out.println("未接受消息，退出");
//			return;
//		}
//		for (ConsumerRecord<String,byte[] > consumerRecord:consumerRecords){
//			Values values=processMqMsg(consumerRecord.value());
//			outputCollector.emit(declareName,values);
//		}
			Ourjstorm ourjstorm=new Ourjstorm(String.valueOf(i++));
			Values values = processMqMsg(ourjstorm);
			//如果指定了消息流ID，发射时也要指定，不指定则用默认流ID
			outputCollector.emit(declareName,values);
			System.out.println("spout********126"+ourjstorm.toString());
	}

	/**
	 * 定义Fields，和values相对应
	 * @param outputFieldsDeclarer
	 * 1.使用自定义ID的消息流，在topology中声明bolt需要指定消息流ID
	 * 2.消息源可以发射多条消息流stream，可以定义多个消息流
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		//使用默认的ID消息流，会分配个值为‘default’默认的id。
		if (Strings.isNullOrEmpty(declareName)){
			outputFieldsDeclarer.declare(new Fields("key","message"));
			return;
		}
		//自定义ID的消息流
		outputFieldsDeclarer.declareStream(declareName,new Fields("key","message"));
//		outputFieldsDeclarer.declareStream(declareName,new Fields("key1","message1"));
	}

	/**
	 * 封装Values，是个可变数组，传入需要的字段，但是对应Fields的key值需要定义
	 * @param
	 * @return
	 */
//	private Values processMqMsg(byte[] msg) {
//		//将传输数组转成需要对象
//		Ourjstorm ourjstorm = JSON.parseObject(msg, Ourjstorm.class);
//		return new Values(ourjstorm.getId(),ourjstorm);
//	}

	private Values processMqMsg(Ourjstorm ourjstorm) {
		//将传输数组转成需要对象
		return new Values(ourjstorm.getId(),ourjstorm);
	}


	@Override
	public void ack(Object msgId) {
		super.ack(msgId);
		System.out.println("发送成功"+msgId);
	}

	@Override
	public void fail(Object msgId) {
		System.out.println("发送失败"+msgId);
		super.fail(msgId);
	}
}
