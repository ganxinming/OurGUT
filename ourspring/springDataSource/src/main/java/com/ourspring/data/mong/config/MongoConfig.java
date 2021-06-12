package com.ourspring.data.mong.config;


import com.mongodb.client.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author ganxinming
 * @createDate 2021/6/10
 * @description
 */
//@Configuration
//public class MongoConfig {
//	private final Logger log = LoggerFactory.getLogger(MongoConfig.class);
//
//
//
//	public @Bean MongoTemplate mongoTemplate( @Autowired MongoClient mongoClient) {
//		return new MongoTemplate(mongoClient, "mydatabase");
//	}
//
//}
