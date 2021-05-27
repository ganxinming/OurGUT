package com.ourgut.ourspring.mybatisplus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ganxinming
 * @createDate 2021/5/27
 * @description
 */
@Configuration
@MapperScan("com.ourgut.ourspring.mybatisplus.mapper")
@EnableTransactionManagement  // 开启自动管理事务
public class MyBatisPlusConfig {
	// 注册乐观锁插件
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor(){
		return new OptimisticLockerInterceptor();
	}

	// 配置分页
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	//配置逻辑删除
	@Bean
	public ISqlInjector sqlInjector(){
		return new LogicSqlInjector();
	}

	/**
	 * SQL执行效率插件
	 * 如果超过执行时间，则会报错
	 */
	@Bean
	@Profile({"dev","test"})// 设置 dev test 环境开启,即变量spring.profiles.active
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		performanceInterceptor.setMaxTime(1);  // 设置sql最大执行时间，单位ms，如果超过了，则不执行
		performanceInterceptor.setFormat(true);  // 开启sql格式化
		performanceInterceptor.setWriteInLog(true);
		return performanceInterceptor;
	}

}
