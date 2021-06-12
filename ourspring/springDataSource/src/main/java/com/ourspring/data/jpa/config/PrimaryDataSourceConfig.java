package com.ourspring.data.jpa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description 类似数据源的配置就是分三步：1.读取配置参数 2.设置参数，生成数据源 3.利用数据源生成jdbcTemplate(JPA无需这一步)
 */
@Configuration
public class PrimaryDataSourceConfig {

	@Primary
	@Bean(name = "primaryDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "primaryDataSource")
	public DataSource dataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	/**
	 * 该方法仅在需要使用JdbcTemplate对象时选用
	 *
	 * @param dataSource 注入名为primaryDataSource的bean
	 * @return 数据源JdbcTemplate对象
	 */
	@Primary
	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
