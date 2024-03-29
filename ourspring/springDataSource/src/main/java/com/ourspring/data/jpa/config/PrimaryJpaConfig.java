package com.ourspring.data.jpa.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description JPA多数据源配置 - 主 JPA 配置
 *
 * 多数据源的dao层，entity层的包名都需要分开，不能统一放在一个包下
 *
 * JPA配置：1.创建 主库实体管理工厂 2.创建主库实体管理器 3.创建事务管理器
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//		// repository包名
//		basePackages = PrimaryJpaConfig.REPOSITORY_PACKAGE,
//		// 实体管理bean名称
//		entityManagerFactoryRef = "primaryEntityManagerFactory",
//		// 事务管理bean名称
//		transactionManagerRef = "primaryTransactionManager")
public class PrimaryJpaConfig {
	static final String REPOSITORY_PACKAGE = "com.ourspring.data.jpa.dao.user";
	private static final String ENTITY_PACKAGE = "com.ourspring.data.jpa.entity.primary";


	/**
	 * 扫描spring.jpa.primary开头的配置信息
	 *
	 * @return jpa配置信息
	 */
	@Primary
	@Bean(name = "primaryJpaProperties")
	@ConfigurationProperties(prefix = "spring.jpa.primary")
	public JpaProperties jpaProperties() {
		return new JpaProperties();
	}

	/**
	 * 获取主库实体管理工厂对象
	 *
	 * @param primaryDataSource 注入名为primaryDataSource的数据源
	 * @param jpaProperties     注入名为primaryJpaProperties的jpa配置信息
	 * @param builder           注入EntityManagerFactoryBuilder
	 * @return 实体管理工厂对象
	 */
	@Primary
	@Bean(name = "primaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource, @Qualifier("primaryJpaProperties") JpaProperties jpaProperties, EntityManagerFactoryBuilder builder) {
		return builder
				// 设置数据源
				.dataSource(primaryDataSource)
				// 设置jpa配置
				.properties(jpaProperties.getProperties())
				// 设置实体包名
				.packages(ENTITY_PACKAGE)
				// 设置持久化单元名，用于@PersistenceContext注解获取EntityManager时指定数据源
				.persistenceUnit("primaryPersistenceUnit").build();
	}

	/**
	 * 获取实体管理对象
	 *
	 * @param factory 注入名为primaryEntityManagerFactory的bean
	 * @return 实体管理对象
	 */
	@Primary
	@Bean(name = "primaryEntityManager")
	public EntityManager entityManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

	/**
	 * 获取主库事务管理对象
	 *
	 * @param factory 注入名为primaryEntityManagerFactory的bean
	 * @return 事务管理对象
	 */
	@Primary
	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}

}

