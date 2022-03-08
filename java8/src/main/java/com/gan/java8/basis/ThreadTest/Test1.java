package com.gan.java8.basis.ThreadTest;

/**
 * @Author Badribbit
 * @create 2019/6/8 9:26
 * @Define
 * @Tutorials
 */
public class Test1 {
    public static void main(String[] args) {
        ThreadA A=new ThreadA();
        ThreadB B=new ThreadB();
        Thread tA=new Thread(A,"A");
        Thread tB=new Thread(B,"B");
        tA.start();tB.start();
    }

}
class ThreadA implements Runnable{

    @Override
    public void run(){
        while(true) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ThreadB implements Runnable{

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
