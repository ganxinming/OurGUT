package com.gan.kafka;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author ganxinming
 * @createDate 2020/10/21
 * @description
 */
public class ProducerTest {

	public static AtomicInteger atomicInteger=new AtomicInteger();
	public static String topic;
	public static int partition;
	public static void main(String[] args) {
		KafkaProducer<String, String> producer = MyKafkaProducer.getKafkaProducer();
		new Thread(()->{
			while(true){
				String message="message";
				//发送消息，key和partition指定一个
				producer.send(new ProducerRecord<String, String>(topic,partition,null,message));
				if (atomicInteger.incrementAndGet() == 1000){
					System.out.println("发送1000千次停止");
					break;
				}
			}
		});
	}
}
