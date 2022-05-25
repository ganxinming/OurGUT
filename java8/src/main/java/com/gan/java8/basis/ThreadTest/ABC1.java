package com.gan.java8.basis.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Badribbit
 * @create 2019/6/9 15:47
 * @Define 按顺序打印ABC
 * @Tutorials
 * @Opinion 1.发现一个特点，若是想要一个线程放弃锁，一般都是while (flag % 3 !=  0) ,利用不等于，而不是等于。
 * 2.其实三个线程按顺序打印和demo1中的差不多，都是通过判断state，只不过boolean的flag只有两种状态，所以只能让两个线程按顺序执行。
 * 这里flag可以通过flag % 3来控制三个。那四个？五个呢？都是一样。但是在这里，随着线程增加，Condition的个数也会增加。
 * 3.以后对于线程题目，直接用ReentrantLock+Condition的方法，爽的一批。
 */
public class ABC1 {
    private static int flag=0;
    private static ReentrantLock lock=new ReentrantLock();
    private static Condition conditionA=lock.newCondition();
    private static Condition conditionB=lock.newCondition();
    private static Condition conditionC=lock.newCondition();

    static class PrintA extends Thread{

        @Override
        public void run() {
            super.run();
            for (int i=0;i<12;i++){
                    try {
                        lock.lock();
                        while (flag % 3 !=  0) {
                            conditionA.await();
							System.out.println("A空转");
                        }
                            System.out.println("第"+(i+1)+"次:A");
                            flag++;
                            conditionB.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        lock.unlock();
                    }

            }

        }
    }

    static class PrintB extends Thread{

        @Override
        public void run() {
            super.run();
            for (int i=0;i<12;i++){
                try {
                    lock.lock();
                    while (flag % 3  != 1) {
                        conditionB.await();
						System.out.println("B空转");
                    }
                        System.out.println("第"+(i+1)+"次:B");
                        flag++;
                        conditionC.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }

            }

        }
    }
    static class PrintC extends Thread{

        @Override
        public void run() {
            super.run();
            for (int i=0;i<12;i++){
                try {
                    lock.lock();
                    while (flag % 3  != 2) {
                        conditionC.await();
						System.out.println("C空转");
                    }
                        System.out.println("第"+(i+1)+"次:C");
                        flag++;
                        conditionA.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }

            }

        }
    }

    public static void main(String[] args) {
        new Thread(new PrintA()).start();
        new Thread(new PrintB()).start();
        new Thread(new PrintC()).start();
    }
}
