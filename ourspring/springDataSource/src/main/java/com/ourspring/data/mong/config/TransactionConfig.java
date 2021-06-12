package com.ourspring.data.mong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * @author ganxinming
 * @createDate 2021/6/10
 * @description
 */
//@Configuration
//public class TransactionConfig {
//
//	@Bean
//	MongoTransactionManager transactionManager(@Autowired MongoDatabaseFactory factory){
//		return new MongoTransactionManager(factory);
//	}
//}