package com.gan.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.sun.tools.javac.util.ArrayUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * @author ganxinming
 * @createDate 2020/10/21
 * @description
 */
public class ConsumerTest {

	public static AtomicInteger messageNum = new AtomicInteger();

	public static int MESSAGE_POLL_TIMEOUT = 1000;

	private static String topicName;

	public static void main(String[] args) {
		KafkaConsumer<String, String> consumer = MyKafkaConsumer.getConsumer();
		String topicArry[] = topicName.split(",");
		//订阅主题模式
		consumer.subscribe(Arrays.asList(topicArry));

		new Thread(() -> {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(MESSAGE_POLL_TIMEOUT));
			while (true) {
				for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
					System.out.println("key:" + consumerRecord.key());
					System.out.println("value:" + consumerRecord.value());
					System.out.println("offset:" + consumerRecord.offset());
					System.out.println("partition:" + consumerRecord.partition());
					System.out.println("topic:" + consumerRecord.topic());
					if(messageNum.incrementAndGet() == 1000){
						System.out.println("接受1000次停止运行");
						break;
					}
				}
			}
		}).start();

	}

}
