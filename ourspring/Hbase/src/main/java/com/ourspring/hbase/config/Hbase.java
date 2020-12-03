package com.ourspring.hbase.config;

/**
 * @author ganxinming
 * @createDate 2020/12/1
 * @description
 */

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hbase {

	private static Logger logger = LoggerFactory.getLogger(Hbase.class);

	private Configuration conf;

	private volatile Connection connection;


	public Hbase(Configuration conf) throws IOException {
		this.conf = conf;
		this.connection = ConnectionFactory.createConnection(conf);
	}

	public Connection getConnection() {
		if (connection == null || connection.isClosed()) {
			synchronized (this) {
				if (connection == null || connection.isClosed()) {
					try {
						connection = ConnectionFactory.createConnection(conf);
					} catch (IOException e) {
						logger.error("Hbase 建立链接失败", e);
					}

				}
			}
		}
		return connection;
	}

	/**
	 * 建表
	 * @param nameSpace
	 * @param tableName
	 * @param family
	 * @param splitKeys 非必须
	 */
	public void createTable(String nameSpace, String tableName, String family, byte[][] splitKeys) {
		try {
			Connection connection = getConnection();
			Admin admin = connection.getAdmin();
			//表空间和表名也可合在一起，看API
			TableName tb_stu1 = TableName.valueOf(nameSpace, tableName);
			// 表的描述构建器
			TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(tb_stu1);
			// 列族的构建器
			ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder =
					ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(family));
			// 构建列族描述器
			ColumnFamilyDescriptor columnFamilyDescriptor = columnFamilyDescriptorBuilder.build();
			// 这是建立单个列族，还可建立多个，查API
			builder.setColumnFamily(columnFamilyDescriptor);
			// 表的描述器
			TableDescriptor descriptor = builder.build();
			//预分region表: 对表的数据进行合理的规划 , 将数据存储不同的region中
			admin.createTable(descriptor, splitKeys);
			admin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入数据(多条或单条)
	 * @param tableName
	 * @param rowKey
	 * @param familyName
	 * @param columnMapValue
	 * @throws IOException
	 */
	public void addData(String tableName, String rowKey, String familyName, Map<String, String> columnMapValue)
			throws IOException {

		Table table = getConnection().getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));//设置rowkey
		for (Map.Entry<String, String> element : columnMapValue.entrySet()) {
			// 列族、属性、值
			put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(element.getKey()), Bytes.toBytes(element.getValue()));
		}
		table.put(put);
		table.close();
	}

	/**
	 * 获取列的值
	 * @param tableName
	 * @param rowKey
	 * @param family
	 * @param qualifier
	 * @return
	 */
	public String getValue(String tableName, String rowKey, String family, String qualifier) {
		Table table = null;
		try {
			table = getConnection().getTable(TableName.valueOf(tableName));

			//进行get操作
			Get get = new Get(rowKey.getBytes());
			//返回指定列族、列名，避免rowKey下所有数据
			get.addColumn(family.getBytes(), qualifier.getBytes());

			Result rs = table.get(get);
			Cell cell = rs.getColumnLatestCell(Bytes.toBytes(family), Bytes.toBytes(qualifier));

			String value = null;
			if (cell != null) {
				value = Bytes.toString(CellUtil.cloneValue(cell));
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}




	/**
	 * Update table.
	 *
	 * @param tableName  the table name
	 * @param rowKey     the row key
	 * @param familyName the family name
	 * @param columnName the column name
	 * @param value      the value
	 * @throws IOException the io exception
	 */

	public void updateTable(String tableName, String rowKey,
			String familyName, String columnName, String value)
			throws IOException {

		Table table = getConnection().getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));
		put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName),
				Bytes.toBytes(value));
		table.put(put);
		table.close();
	}


	/**
	 * Batch add data.
	 *
	 * @param tableName the table name
	 * @param puts      the puts
	 * @throws Exception the exception
	 */
	public void batchAddData(String tableName, List<Put> puts) throws Exception {
		Connection connection = getConnection();
		final BufferedMutator.ExceptionListener exceptionListener = (exception, mutator) -> {
			for (int i = 0; i < exception.getNumExceptions(); i++) {
				logger.error("Failed to sent put " + exception.getRow(i) + ".");
			}
		};
		BufferedMutatorParams params = new BufferedMutatorParams(TableName.valueOf(tableName))
				.listener(exceptionListener);
		params.writeBufferSize(5 * 1024 * 1024);
		final BufferedMutator mutator = connection.getBufferedMutator(params);
		try {
			mutator.mutate(puts);
			mutator.flush();
		} finally {
			mutator.close();
		}
	}

	/*
	 * 根据rwokey查询
	 *
	 * @rowKey rowKey
	 *
	 * @tableName 表名
	 */
	public Result getResult(String tableName, String rowKey)
			throws IOException {
		Get get = new Get(Bytes.toBytes(rowKey));
		Table table = getConnection().getTable(TableName.valueOf(tableName));
		Result result = table.get(get);
		return result;
	}

	/**
	 * 查询所有
	 * @param tableName
	 * @return
	 * @throws IOException
	 */
	public List<Map<String,String>>  getResultScan(String tableName) throws IOException {
		List<Map<String,String>> ret=Lists.newArrayList();
		Scan scan = new Scan();
		ResultScanner rs = null;
		Table table = getConnection().getTable(TableName.valueOf(tableName));
		try {
			rs = table.getScanner(scan);
			for (Result r : rs) {
				Map<String, String> map = Maps.newHashMap();
				//Cell 就是键值对<列名，value>
				for (Cell cell : r.rawCells()) {
					//一般都是用CellUtil 取得列名和属性值
					map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
				}
				ret.add(map);
			}
		} finally {
			rs.close();
		}
		return ret;
	}

	/**
	 * 查询指定列簇所有
	 * @param tableName
	 * @param rowKey
	 * @param familyName
	 * @return
	 * @throws IOException
	 */
	public Map<String,String> getResultByColumn(String tableName, String rowKey,
			String familyName) throws IOException {
		Table table = getConnection().getTable(TableName.valueOf(tableName));
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addFamily(Bytes.toBytes(familyName)); // 获取指定列族和列修饰符对应的列
		Result result = table.get(get);
		Map<String, String> map = Maps.newHashMap();
		for(Cell cell:result.rawCells()){
			map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
		}
		return map;
	}

	/*
	 * 查询某列数据的多个版本
	 *
	 * @tableName 表名
	 *
	 * @rowKey rowKey
	 *
	 * @familyName 列族名
	 *
	 * @columnName 列名
	 */
	public Result getResultByVersion(String tableName, String rowKey,
			String familyName, String columnName) throws IOException {
		Table table = getConnection().getTable(TableName.valueOf(tableName));
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
		get.setMaxVersions(5);
		Result result = table.get(get);
		return result;
	}

	/**
	 * 删除指定列
	 * @param tableName
	 * @param rowKey
	 * @param falilyName
	 * @param columnName
	 * @throws IOException
	 */
	public void deleteColumn(String tableName, String rowKey, String falilyName, String columnName) throws IOException {
		Table table = null;
		try {
			table = getConnection().getTable(TableName.valueOf(tableName));
			Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
			deleteColumn.addColumn(Bytes.toBytes(falilyName),
					Bytes.toBytes(columnName));

			table.delete(deleteColumn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 删除指定 列簇
	 * @param tableName
	 * @param rowKey
	 * @param family
	 * @throws IOException
	 */
	public void deleteRow(String tableName, String rowKey, String family) throws IOException {
		Table table = null;
		try {
			table = getConnection().getTable(TableName.valueOf(tableName));
			Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
			deleteColumn.addFamily(Bytes.toBytes(family));
			table.delete(deleteColumn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (table != null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * 删除表
	 *
	 * @tableName 表名
	 */
	public void deleteTable(String tableName) throws IOException {
		Admin admin = getConnection().getAdmin();
		admin.disableTable(TableName.valueOf(tableName));
		admin.deleteTable(TableName.valueOf(tableName));
		logger.info(tableName + "is deleted!");
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {
				logger.error("returnConnection error:", e);
			}
		}
	}

	/**
	 * 范围扫描
	 * @param tableName
	 * @param columnFamily
	 * @param column
	 * @param startRowKey
	 * @param endRowKey
	 * @param order
	 * @param limit
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, String>> scanRange(String tableName,String columnFamily,String column,
			String startRowKey,String endRowKey,String order,Integer limit ) throws IOException {
		List<Map<String, String>> ret = Lists.newArrayList();
		Table table = getConnection().getTable(TableName.valueOf(tableName));
		Scan scan = new Scan();

		scan.addColumn(Optional.ofNullable(columnFamily).map(x -> Bytes.toBytes(x)).orElse(null)
				,Optional.ofNullable(column).map(x -> Bytes.toBytes(x)).orElse(null))
				.withStartRow(Bytes.toBytes(startRowKey))
				.withStopRow(Bytes.toBytes(endRowKey),true);

		//降序,默认升序
		if("desc".equals(order)){
			scan.withStartRow(Bytes.toBytes(endRowKey))
					.withStopRow(Bytes.toBytes(startRowKey),true)
					.setReversed(true);
		}
		if(limit != null){
			scan.setLimit(limit);
		}
		//Result 相当于每行数据
		ResultScanner rs = table.getScanner(scan);
		for (Result r : rs) {
			Map<String, String> map = Maps.newHashMap();
			//Cell 就是键值对<列名，value>
			for (Cell cell : r.rawCells()) {
				//一般都是用CellUtil 取得列名和属性值
				map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
			}
			ret.add(map);
		}
		return ret;
	}

	/**
	 *
	 * @param tableName
	 * @param columnFamily
	 * @param column
	 * @return
	 * @throws IOException
	 * 所有比较器均继承自ByteArrayComparable抽象类
	 * BinaryComparator : 使用Bytes.compareTo(byte []，byte [])按字典序比较指定的字节数组。
	 * BinaryPrefixComparator : 按字典序与指定的字节数组进行比较，但只比较到这个字节数组的长度。
	 * RegexStringComparator : 使用给定的正则表达式与指定的字节数组进行比较。仅支持EQUAL和NOT_EQUAL操作。
	 * SubStringComparator : 测试给定的子字符串是否出现在指定的字节数组中，比较不区分大小写。仅支持EQUAL和NOT_EQUAL操作。
	 * NullComparator ：判断给定的值是否为空。
	 * BitComparator ：按位进行比较。
	 *
	 * 过滤器种类：
	 * RowFilter ：基于行键来过滤数据；
	 * FamilyFilterr ：基于列族来过滤数据；
	 * QualifierFilterr ：基于列限定符（列名）来过滤数据；
	 * ValueFilterr ：基于单元格(cell) 的值来过滤数据；
	 * DependentColumnFilter ：指定一个参考列来过滤其他列的过滤器，过滤的原则是基于参考列的时间戳来进行筛选 。
	 */
	public List<Map<String, String>> scanFilter(String tableName,String columnFamily,String column,String rowkey) throws IOException {
		Table table = getConnection().getTable(TableName.valueOf(tableName));
		Scan scan = new Scan();
		List<Map<String, String>> ret = Lists.newArrayList();
		//CompareOperator中有很多比较方式
		//行键过滤器
		RowFilter rowFilter = new RowFilter(CompareOperator.GREATER, new BinaryComparator("k0001".getBytes()));
		//列簇过滤器
		FamilyFilter familyFilter = new FamilyFilter(CompareOperator.EQUAL, new SubstringComparator("cf1"));
		//属性过滤器
		QualifierFilter qualifierFilter = new QualifierFilter(CompareOperator.EQUAL, new BinaryComparator("id".getBytes()));

		FilterList where = new FilterList(familyFilter, rowFilter, qualifierFilter);

		scan.setFilter(where);

		ResultScanner scanner = table.getScanner(scan);
		for (Result r : scanner) {
			Map<String, String> map = Maps.newHashMap();
			//Cell 就是键值对<列名，value>
			for (Cell cell : r.rawCells()) {
				//一般都是用CellUtil 取得列名和属性值
				map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
			}
			ret.add(map);
		}
		return null;
	}
}

