package com.ourGUT.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ganxinming
 * @createDate 2021/5/17
 * @description
 */
public class TestTimer {
	public static void main(String[] args) {
		Timer timer = new Timer();
		/**
		 * 10秒后执行，1秒执行一次
		 */
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println(123);
			}
		},10000,1000);

	}
}
