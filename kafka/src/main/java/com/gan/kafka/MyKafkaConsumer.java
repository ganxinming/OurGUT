package com.gan.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @author ganxinming
 * @createDate 2020/10/20
 * @description
 */
public class MyKafkaConsumer {
	/**
	 * 这些可配置到文件中
	 */
	private static String kafkaStr;
	private static String GROUPID;
	private static String brokerList;

	public static KafkaConsumer<String, String> getConsumer(){
		
		Properties props = new Properties();
		props.put("group.id", GROUPID);
		props.put("bootstrap.servers", brokerList);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", 1000);
		props.put("session.timeout.ms", 30000);
		props.put("max.poll.records", 100);
		props.put("enable.auto.commit", "true");

		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		return new KafkaConsumer<String, String>(props);
	}
}
