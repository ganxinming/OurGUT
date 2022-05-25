package com.gan.java8.basis.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Badribbit
 * @create 2019/6/4 19:15
 * @Define 按顺序打印ABC，通过静态内部类实现。一个lock，一个标志位作用。
 * 这里并没有用等待和唤醒，他通过将i++放在while里面，只有执行成功i才能++，不执行成功则一直for循环等待且没有i++.
 * 相当于将线程一直轮询，取代了三个唤醒和等待。
 * 一直轮询，也会消耗线程，所以需要等待和唤醒
 * @Tutorials
 * ????
 * 不知道为什么必须要用Condition去wati，换成lock.wait，就不行，那么只要记住lock的话，一定要用condition唤醒
 */
public class ABC {

    private static Lock lock = new ReentrantLock();
    private static Condition con = lock.newCondition();
    private static int state = 0;// 用state来判断轮到谁执行
    private static final int RUN_NUMBER=10;//表示循环的次数



    public static void main (String[] args){
    	new Thread(()->{
    		for(int i=0;i<10;i++){
				lock.lock();
				try {
//					System.out.println("A lock");
					while(state%3 != 0){
//						System.out.println("A空转");
						con.await();
					}
					System.out.println("第："+i+"A");
					state++;
					con.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
    	}).start();
        new Thread(()->{
			for(int i=0;i<10;i++){
				lock.lock();
				try {
//					System.out.println("B lock");
					while(state%3 != 1){
//						System.out.println("B空转");
						con.await();
					}
					System.out.println("第："+i+"B");
					state++;
					con.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}).start();
        new Thread(()->{
			for(int i=0;i<10;i++){
				lock.lock();
				try {
//					System.out.println("C lock");
					while(state%3 != 2){
//						System.out.println("C空转");
						con.await();
					}
					System.out.println("第："+i+"C");
					state++;
					con.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}).start();
    }
}