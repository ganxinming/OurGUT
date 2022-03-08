package com.gan.java8.basis.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Badribbit
 * @create 2019/6/8 15:32
 * @Define 写两个线程，一个线程打印1-52，另一个线程打印A-Z，打印顺序是12A34B...5152Z；
 * @Tutorials
 * @Opinion
 */
public class Demo2 {
    static class NumAndCharPrint{
        private volatile  boolean flag =false; //是否锁住
        private ReentrantLock lock=new ReentrantLock();
        private Condition condition=lock.newCondition();
        private volatile  int sum=1;
        public void printChar(){
            try {
                lock.lock();
                while (!flag){
                    //等待并释放锁
                    condition.await();
                }
                System.out.print((char)(sum+++96));
                flag=false;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
        public void printNum(){
                try {
                    lock.lock();
                    while (flag){
                    //等待并释放锁
                    condition.await();
                    }
                    System.out.print(sum*2-1);
                    System.out.print(sum*2);
                    flag=true;
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
        }


    }

    public static void main(String[] args) {
        NumAndCharPrint p=new NumAndCharPrint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<26;i++){
                    p.printNum();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<26;i++){
                    p.printChar();
                }
            }
        }).start();
    }
}

