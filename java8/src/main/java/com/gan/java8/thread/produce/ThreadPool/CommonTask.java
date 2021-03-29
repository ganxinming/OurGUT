package com.gan.java8.thread.produce.ThreadPool;

/**
 * @author ganxinming
 * @createDate 2021/3/29
 * @description
 */
public interface CommonTask {

	default void beforeExecute() {

	}

	default void afterExecute() {

	}

	void doExecute();
}