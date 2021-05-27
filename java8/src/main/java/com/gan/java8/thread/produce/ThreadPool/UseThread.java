package com.gan.java8.thread.produce.ThreadPool;

/**
 * @author ganxinming
 * @createDate 2021/5/21
 * @description
 */
public class UseThread {
	public static void main(String[] args) {
		CaseThreadPoolManager.submitToCaseThreadPool(new AbstractCommonTask("mongo") {
			@Override
			public void doExecute() {

			}
		});
	}
}
