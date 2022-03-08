package com.gan.java8.basis.ThreadTest.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Badribbit
 * @create 2019/8/23 20:01
 * @Define
 * @Tutorials利用一个condition实现三个线程。注意：一定要signalAll().
 * @Opinion
 */
public class test5 {
    public static ReentrantLock lock=new ReentrantLock();
    //0：表示A，1：表示B，2：表示C
    public static volatile int flag=0;
    public static Condition condition=lock.newCondition();
    public static void main(String[] args) {
        new Thread(()->{

            for (int i=0;i<10;i++){

                try {
                    lock.lock();
                while(flag%3 != 0){
                    condition.await();
                    }
                    System.out.println("这是第"+i+"次");
                    System.out.println("A");
                    flag++;
                    condition.signalAll();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            finally {

                lock.unlock();
                }
            }
        }).start();

        new Thread(()->{

            for (int i=0;i<10;i++){

                try {
                    lock.lock();
                    while(flag%3 != 1){
                        condition.await();
                    }

                    System.out.println("B");
                    flag++;
                    condition.signalAll();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {

                    lock.unlock();
                }
            }
        }).start();

        new Thread(()->{

            for (int i=0;i<10;i++){

                try {
                    lock.lock();
                    while(flag%3 != 2){
                        condition.await();
                    }

                    System.out.println("C");
                    flag++;
                    condition.signalAll();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
            }
        }).start();
    }

}
