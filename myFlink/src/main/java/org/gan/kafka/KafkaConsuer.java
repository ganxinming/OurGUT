package org.gan.kafka;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import org.apache.flink.shaded.guava18.com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.gan.model.Metric;

/**
 * @author ganxinming
 * @createDate 2022/1/21
 * @description
 */
public class KafkaConsuer {

	public static final String broker_list = "10.6.0.4:15386";

	public static final String topic = "UIC_CUSTOMER_LOGIN";  // kafka topic，Flink 程序中需要和这个统一

	public static final String group="RISK_JSTORM_UIC_GROUP";

	public static KafkaConsumer  consumer() throws InterruptedException {
		Properties props = new Properties();
		props.put("bootstrap.servers", broker_list);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("group.id", group);
		KafkaConsumer consumer = new KafkaConsumer<String,String>(props);
		return consumer;
	}

	public static  void main(String[] args) throws InterruptedException {
		KafkaConsumer consumer=consumer();
		consumer.subscribe(Arrays.asList(topic));
		while(true){
			ConsumerRecords poll = consumer.poll(100);
			Iterator iterator = poll.iterator();
			while(iterator.hasNext()){
				Object next = iterator.next();
				System.out.println("测试数据："+next.toString());
			}
			Thread.sleep(1000);
		}
	}
}
