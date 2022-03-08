package com.gan.java8.basis.ThreadTest.test;

/**
 * @Author Badribbit
 * @create 2019/8/23 20:53
 * @Define
 * @Tutorials
 * @Opinion
 */
public class test3 {
    private static  int num=0;
    public  void A(){
        for (int i=0;i<10;){
            synchronized (this){
                while (num%3 == 0){
                    for (int j=0;j<10;j++){
                        System.out.println("A");
                    }
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
                    for (int j=0;j<10;j++){
                        System.out.println("B");
                    }
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
                    for (int j=0;j<10;j++){
                        System.out.println("C");
                    }
                    num++;
                    i++;
                }
            }
        }
    }



    public static void main(String[] args) {
        test3 test3=new test3();
        new Thread(()->{

                test3.A();

        }).start();
        new Thread(()->{

                test3.B();

        }).start();
        new Thread(()->{

                test3.C();

        }).start();
    }
}
