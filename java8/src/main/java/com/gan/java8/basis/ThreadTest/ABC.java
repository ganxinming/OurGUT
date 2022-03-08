package com.gan.java8.basis.ThreadTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Badribbit
 * @create 2019/6/4 19:15
 * @Define 按顺序打印ABC，通过静态内部类实现。一个lock，一个标志位作用。
 * 这里并没有用等待和唤醒，他通过将i++放在while里面，只有执行成功i才能++，不执行成功则一直for循环等待且没有i++.
 * 相当于将线程一直轮询，取代了三个唤醒和等待。
 * @Tutorials
 */
public class ABC {

    private static Lock lock = new ReentrantLock();
    private static int state = 0;// 用state来判断轮到谁执行
    private static final int RUN_NUMBER=10;//表示循环的次数

    //A线程
    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < RUN_NUMBER;i++) {
                try {
                    lock.lock();//获取锁定
                    //System.out.println("A get lock");
                    while (state % 3 == 0) {
                        System.out.println("第"+(i+1)+"次:");
                        System.out.println("A");
                        state++;
                    }
                }
                finally {
                    lock.unlock();//释放锁定,不释放锁定，会被该线程一直保持
                }
            }
        }
    }

    //B线程
    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < RUN_NUMBER;) {
                try {
                    lock.lock();
                    //System.out.println("B get lock");
                    while (state % 3 ==1) {
                        System.out.println("B");
                        state++;
                        i++;
                    }
                }
                finally {
                    lock.unlock();
                }

            }
        }
    }

    //C线程
    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < RUN_NUMBER;) {
                try {
                    lock.lock();
                    //System.out.println("C get lock");
                    // 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                    while (state % 3 == 2) {
                        System.out.println("C");
                        state++;
                        i++;
                    }
                }
                // unlock()操作必须放在finally块中
                finally {
                    lock.unlock();
                }

            }
        }
    }

    public static void main (String[] args){
        new ThreadC().start();
        new ThreadB().start();
        new ThreadA().start();
    }
}