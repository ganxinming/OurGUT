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
import com.gan.jstorm.Ourjstorm;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author ganxinming
 * @createDate 2020/10/23
 * @description
 */
public class SecondSpout extends BaseRichSpout {

	private SpoutOutputCollector outputCollector;

	private String declareName;

	private KafkaConsumer kafkaConsumer;

	public SecondSpout(String declareName) {
		this.declareName=declareName;
	}

	@Override
	public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
		this.outputCollector=spoutOutputCollector;
	}

	@Override
	public void nextTuple() {
		//使用byte[]便于对象传输
		ConsumerRecords<String,byte[] > consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(100));
		if (consumerRecords.isEmpty() || consumerRecords.count() <=0){
			System.out.println("未接受消息，退出");
			return;
		}
		for (ConsumerRecord<String,byte[] > consumerRecord:consumerRecords){
			Values values=processMqMsg(consumerRecord.value());
			outputCollector.emit(declareName,values);
		}
		Ourjstorm ourjstorm=new Ourjstorm("123");
		byte[] bytes = JSON.toJSONString(ourjstorm).getBytes();

	}

	/**
	 * 定义Fields，和values相对应
	 * @param outputFieldsDeclarer
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields("key","message"));
	}

	/**
	 * 封装Values，是个可变数组，传入需要的字段，但是对应Fields的key值需要定义
	 * @param msg
	 * @return
	 */
	private Values processMqMsg(byte[] msg) {
		//将传输数组转成需要对象
		Ourjstorm ourjstorm = JSON.parseObject(msg, Ourjstorm.class);
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
