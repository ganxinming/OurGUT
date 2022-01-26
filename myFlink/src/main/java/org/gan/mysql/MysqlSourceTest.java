package org.gan.mysql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author ganxinming
 * @createDate 2022/1/21
 * @description org.gan.mysql.MysqlSourceTest
 */
public class MysqlSourceTest {
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		env.addSource(new SourceFromMySQL()).print();

		env.execute("Flink add data sourc");
	}
}
