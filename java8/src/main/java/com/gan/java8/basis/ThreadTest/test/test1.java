package com.gan.java8.basis.ThreadTest.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Badribbit
 * @create 2019/8/23 20:01
 * @Define
 * @Tutorials利用三个condition完成三个线程。
 * @Opinion
 */
public class test1 {
    public static ReentrantLock lock=new ReentrantLock();
    //0：表示A，1：表示B，2：表示C
    public static volatile int flag=0;
    public static Condition conditionA=lock.newCondition();
    public static Condition conditionB=lock.newCondition();
    public static Condition conditionC= lock.newCondition();
    public static void main(String[] args) {
        new Thread(()->{

            for (int i=0;i<10;i++){

                try {
                    lock.lock();
                while(flag%3 != 0){
                    conditionA.await();
                    }
                    System.out.println("这是第"+i+"次");
                    System.out.println("A");
                    flag++;
                    conditionB.signal();
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
                        conditionB.await();
                    }

                    System.out.println("B");
                    flag++;
                    conditionC.signal();
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
                        conditionC.await();
                    }

                    System.out.println("C");
                    flag++;
                    conditionA.signal();
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
