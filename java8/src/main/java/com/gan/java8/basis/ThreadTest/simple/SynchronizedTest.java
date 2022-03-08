package com.gan.java8.basis.ThreadTest.simple;

import java.util.concurrent.TimeUnit;

/**
 * @Author Badribbit
 * @create 2019/6/10 10:52
 * @Define Synchronized关键字测试(五种锁对象)
 * @Tutorials
 * @Opinion 1.new Thread(synchronizedTest)和new Thread(new SynchronizedTest())的区别。
 *          锁当前对象或变量对象时对同一个synchronizedTest就有效，但是对每次new SynchronizedTest()无效，因为这些是新new的对象
 *          锁类对象，对new SynchronizedTest()无效。但是得保证里面使用的变量是静态的，如果不是静态的，实际上那些成员变量还是各有各的默认值,
 *          不能达到统一访问的目的。
 *          2.锁静态方法，里面必须是static变量。锁class对象，没有必须，但是想要统一访问，也要使用static变量。
 */
public class SynchronizedTest implements Runnable{
    private  Object o=new Object();
    private  volatile int con=0;
    private  volatile static int sum=0;
    public void m1() throws InterruptedException {
        synchronized (o) {
            System.out.println(Thread.currentThread().getName() + "的con是:" + con++);
            //TimeUnit提供了更好的时间支持
            TimeUnit.SECONDS.sleep(1);
        }
    }
    public void m2() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "的con是:" + con++);
            Thread.sleep(100);
        }
    }
    public synchronized void m3() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "的con是:" + con++);
            Thread.sleep(100);

    }
    public synchronized static void m4() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "的sum是:" + sum++);
        Thread.sleep(100);
    }
    public void m5() throws InterruptedException {
        synchronized (SynchronizedTest.class) {
            System.out.println(Thread.currentThread().getName() + "的sum是:" + sum++);
            Thread.sleep(100);
        }
    }
    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SynchronizedTest synchronizedTest=new SynchronizedTest();
        for (int i=0;i<9;i++){
            new Thread(synchronizedTest,String.valueOf((char)(i+97))).start();
        }
        /*for (int i=0;i<9;i++){
            new Thread(new SynchronizedTest(),String.valueOf((char)(i+97))).start();
        }*/
    }


}
