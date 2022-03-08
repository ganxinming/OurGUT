package com.gan.java8.basis.ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author Badribbit
 * @create 2019/6/9 16:47
 * @Define  semaphore(信号量)测试
 * @Tutorials
 * @Opinion
 */
public class SemaphoreTest {
    static class TestS extends  Thread{
        private Semaphore semaphore;
        private  int sum;
        public TestS(Semaphore semaphore,int sum){
            this.semaphore=semaphore;
            this.sum=sum;
        }
        @Override
        public void run() {
            try {
                if (semaphore.availablePermits() > 0){
                    System.out.println("顾客["+this.sum+"]进入厕所，有空位");
                }
                else{
                    System.out.println("顾客["+this.sum+"]进入厕所，没空位，排队");
                }
                semaphore.acquire();
                System.out.println("顾客["+this.sum+"]获得坑位");
                Thread.sleep((long) (Math.random()*1000));
                System.out.println("顾客["+this.sum+"]使用完，离开坑位");
                semaphore.release(1000);
                System.out.println("空闲坑位数量"+semaphore.getQueueLength());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {

        ExecutorService pool=Executors.newFixedThreadPool(2);
        Semaphore semaphore=new Semaphore(2);
        for (int i=0;i<10;i++){
            pool.submit(new TestS(semaphore,i));
        }
        pool.shutdown();
        semaphore.acquireUninterruptibly(2);
        System.out.println("使用完毕需要清扫");
        semaphore.release(2);
    }

}
