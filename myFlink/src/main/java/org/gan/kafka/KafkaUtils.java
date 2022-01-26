package org.gan.kafka;

import java.util.Properties;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.gan.model.Metric;
import org.gan.sink.PrintSinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ganxinming
 * @createDate 2022/1/21
 * @description
 */
public class KafkaUtils {

	public static final String broker_list = "10.6.0.4:15386";

	public static final String topic = "UIC_CUSTOMER_LOGIN";  // kafka topic，Flink 程序中需要和这个统一

	public static final String zooKeeperConfig="10.6.0.4:15311";

	public static final String group="RISK_JSTORM_UIC_GROUP";

	public static KafkaConsumer getConsumer() throws InterruptedException {
		Properties props = new Properties();
		props.put("bootstrap.servers", broker_list);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("group.id", group);
		KafkaConsumer consumer = new KafkaConsumer<String,String>(props);
		return consumer;
	}

	public static KafkaProducer getProvider() throws InterruptedException {
		Properties props = new Properties();
		props.put("bootstrap.servers", broker_list);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); //key 序列化
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); //value 序列化
		KafkaProducer producer = new KafkaProducer<String, String>(props);
		return producer;
	}

	public static void send(KafkaProducer producer){
		Metric metric = new Metric("123",System.currentTimeMillis());
		metric.setTimestamp(System.currentTimeMillis());
		metric.setName("mem");
		ProducerRecord record = new ProducerRecord<String, String>(topic, null, null, JSON.toJSONString(metric));
		producer.send(record);
		System.out.println("发送数据: " + JSON.toJSONString(metric));
		producer.flush();
	}

	public static void consumerFlinkKafka() throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties props = new Properties();
		props.put("bootstrap.servers", broker_list);
		props.put("zookeeper.connect", zooKeeperConfig);
		props.setProperty("group.id", group);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("auto.offset.reset", "latest"); //value 反序列化

		DataStreamSource<String> dataStreamSource = env.addSource(new FlinkKafkaConsumer<>(
				topic,  //kafka topic
				new SimpleStringSchema(),  // String 序列化
				props)).setParallelism(1);
		dataStreamSource.print(); //把从 kafka 读取到的数据打印在控制台
		dataStreamSource.addSink(new PrintSinkFunction<>());
		env.execute("Flink add data source");
	}

	public static void main(String[] args) throws Exception {
		consumerFlinkKafka();
	}

	public static void sendMessage() throws InterruptedException {
		KafkaProducer provider = getProvider();
		while(true){
			send(provider);
		}
	}
}
