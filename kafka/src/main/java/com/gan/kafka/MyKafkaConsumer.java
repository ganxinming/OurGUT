package com.gan.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
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
		//设置消费者组
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUPID);
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		//是否自动提交offset
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.TRUE);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
		//一次最大拉取数
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		return new KafkaConsumer<String, String>(props);
	}
}
