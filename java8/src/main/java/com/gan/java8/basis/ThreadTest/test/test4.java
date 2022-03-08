package com.gan.java8.basis.ThreadTest.test;

/**
 * @Author Badribbit
 * @create 2019/8/23 20:53
 * @Define
 * @Tutorials
 * @Opinion
 */
public class test4 {
    private static  int num=0;
    public  void A(){
        for (int i=0;i<10;){
            synchronized (this){
                while (num%3 != 0){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A");
                num++;
                this.notifyAll();
            }
        }
    }

    public  void B(){
        for (int i=0;i<10;){
            synchronized (this){
                while (num%3 != 1){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B");
                num++;
                this.notifyAll();
            }
        }
    }
    public  void C(){
        for (int i=0;i<10;){
            synchronized (this){
                while (num%3 != 2){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("C");
                num++;
                this.notifyAll();
            }
        }
    }



    public static void main(String[] args) {
        test4 test2=new test4();
        new Thread(()->{

                test2.A();

        }).start();
        new Thread(()->{

                test2.B();

        }).start();
        new Thread(()->{

                test2.C();

        }).start();
    }
}
