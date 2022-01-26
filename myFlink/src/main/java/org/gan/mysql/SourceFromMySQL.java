package org.gan.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.gan.model.Feature;

/**
 * @author ganxinming
 * @createDate 2022/1/21
 * @description
 * 自定义mysql数据源，基础RichSourceFunction
 */
public class SourceFromMySQL extends RichSourceFunction<Feature> {
	PreparedStatement ps;
	private Connection connection;

	/**
	 * open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接。
	 *
	 * @param parameters
	 * @throws Exception
	 */
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		connection = getConnection();
		String sql = "select * from rp_basic_feature;";
		ps = this.connection.prepareStatement(sql);
	}

	/**
	 * 程序执行完毕就可以进行，关闭连接和释放资源的动作了
	 *
	 * @throws Exception
	 */
	@Override
	public void close() throws Exception {
		super.close();
		if (connection != null) { //关闭连接和释放资源
			connection.close();
		}
		if (ps != null) {
			ps.close();
		}
	}

	/**
	 * DataStream 调用一次 run() 方法用来获取数据
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void run(SourceContext<Feature> ctx) throws Exception {
		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next()) {
			Feature student = new Feature(
					resultSet.getLong("id"),
					resultSet.getString("code").trim());
			ctx.collect(student);
		}
	}

	@Override
	public void cancel() {
	}

	private static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://10.6.0.16:15381/risk_platform?useUnicode=true&characterEncoding=UTF-8", "caocao", "CcDBInfo*150");
		} catch (Exception e) {
			System.out.println("-----------mysql get connection has exception , msg = "+ e.getMessage());
		}
		return con;
	}
}
