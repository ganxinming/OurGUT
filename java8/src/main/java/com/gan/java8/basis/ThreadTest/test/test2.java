package com.gan.java8.basis.ThreadTest.test;

/**
 * @Author Badribbit
 * @create 2019/8/23 20:53
 * @Define
 * @Tutorials 将i++放在while中，只有成功执行才能i++。否则一直空循环，这样可以替代使用等待wait和唤醒notify。
 * @Opinion
 */
public class test2 {
    private static  int num=0;
    public  void A(){
        for (int i=0;i<10;){
            synchronized (this){
                while (num%3 == 0){
                    System.out.println("A");
                    num++;
                    i++;
                }
            }
        }
    }

    public  void B(){
        for (int i=0;i<10;){
            synchronized (this){
                while (num%3 == 1){
                    System.out.println("B");
                    num++;
                    i++;
                }
            }
        }
    }
    public  void C(){
        for (int i=0;i<10;){
            synchronized (this){
                while (num%3 == 2){
                    System.out.println("C");
                    num++;
                    i++;
                }
            }
        }
    }



    public static void main(String[] args) {
        test2 test2=new test2();
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
