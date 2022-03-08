package com.gan.java8.basis.ThreadTest.second;

/**
 * @Author Badribbit
 * @create 2019/8/12 22:06
 * @Define
 * @Tutorials
 * @Opinion
 */
public class ProducerAndConsumer {
    private volatile int size=0;
    
    public void setApple(){
        synchronized (this){
            while (size >= 5){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产了苹果"+ ++size);
            this.notify();
        }
    }

    public void getApple(){
        synchronized (this){
            while (size <= 0){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费了苹果"+ --size);
            this.notify();
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumer p=new ProducerAndConsumer();
        new Thread(()->{
            for (int i=0;i<10;i++){
                p.getApple();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        new Thread(()->{
            for (int i=0;i<10;i++){
                p.setApple();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
