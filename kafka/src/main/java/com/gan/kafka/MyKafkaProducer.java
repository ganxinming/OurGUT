package com.gan.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author ganxinming
 * @createDate 2020/10/20
 * @description
 */
public class MyKafkaProducer {


//	@Value("${kafka.broke.list}")
	private static String brokeList;


	public static KafkaProducer<String, String> getKafkaProducer() {
		Properties props = new Properties();
		// 声明kafka broker
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokeList);
		/**
		 * 发送消息确认机制
		 * - 0代表producer往集群发送数据不需要等到集群的返回，不确保消息发送成功。安全性最低但是效率最高。
		 * - 1代表producer往集群发送数据只要leader应答就可以发送下一条，只确保leader发送成功。
		 * - all代表producer往集群发送数据需要所有的follower都完成从leader的同步才会发送下一条，确保leader发送成功和所有的副本都完成备份。安全性最高，但是效率最低。(leader和follower全部应答)
		 */
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//		props.put("serializer.class", "kafka.serializer.StringEncoder");
//		props.put("key.serializer.class", "kafka.serializer.StringEncoder");
		//发送方式:异步还是同步(async/sync)
		props.put("producer.type","async");
		//如果topic不存在,自动创建
		props.put("auto.create.topics.enable",Boolean.TRUE);
		return new KafkaProducer<String, String>(props);
	}


}
