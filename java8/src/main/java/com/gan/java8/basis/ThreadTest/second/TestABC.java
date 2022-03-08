package com.gan.java8.basis.ThreadTest.second;

/**
 * @Author Badribbit
 * @create 2019/8/11 22:41
 * @Define
 * @Tutorials
 * @Opinion
 */
public class TestABC {
    private volatile  int state=0;
    public static void main(String[] args) {
        TestABC abc=new TestABC();
        new Thread( ()->{
            for (int i=0;i<10;i++){
                abc.A();
            }
        }).start();
        new Thread( ()->{
            for (int i=0;i<10;i++){
                abc.B();
            }
        }).start();
        new Thread( ()->{
            for (int i=0;i<10;i++){
                abc.C();
            }
        }).start();
    }
    public synchronized void A(){
        while (state != 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A");
        state=1;
        this.notify();
    }
    public synchronized  void B(){
        while (state != 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B");
        state=2;
        this.notify();
    }
    public synchronized  void C(){
        while (state != 2){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("C");
        state=0;
        this.notify();
    }
}
