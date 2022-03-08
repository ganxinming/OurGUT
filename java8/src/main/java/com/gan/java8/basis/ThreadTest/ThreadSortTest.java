package com.gan.java8.basis.ThreadTest;

/**
 * @Author Badribbit
 * @create 2019/6/9 15:02
 * @Define
 * @Tutorials
 * @Opinion
 */

public class ThreadSortTest {

    /**
     * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
     */
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable(){
            public void run(){
                System.out.println(Thread.currentThread().getName()+" -- "+System.currentTimeMillis());
            }
        },"A");

        Thread t2 = new Thread(new Runnable(){
            public void run(){
                System.out.println(Thread.currentThread().getName()+" -- "+System.currentTimeMillis());
            }
        },"B");

        Thread t3 = new Thread(new Runnable(){
            public void run(){
                System.out.println(Thread.currentThread().getName()+" -- "+System.currentTimeMillis());
            }
        },"C");

        t1.start();
        try {
            t1.join();  // 可以看做是主线程中加入了t1,需要等待t1执行完才能执行。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            t2.join();  // t2.join()需要等t2.start()执行之后执行才有效果
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();


    }

}
