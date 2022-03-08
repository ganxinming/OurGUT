package com.gan.java8.basis.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Badribbit
 * @create 2019/6/8 10:43
 * @Define 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 * @Tutorials 运行即可
 * @Opinion
 * PrintTest 1.wait方法是object的方法，可以通过this调用。2.有wait的必会抛异常。
 *           3.使用了wait，必有notify。4.因为wait本质是持有锁，所以必有synchronized伴随（没有这个关键字怎么锁）。
 * PrintTest2  1.相比较PrintTest增加了Condition，增加了锁的粒度。
 * 对于所有线程可见的变量用volatile。
 */
public class Demo1 {
    public static void main(String[] args) {
        PrintTest2 p=new PrintTest2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<50;i++) {
                    p.sub();;
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<50;i++) {
                    p.pMain();;
                }
            }
        }).start();
    }

}

/**
 * 通过synchronized和wait实现
 * 步骤1：判断是否锁住 2.执行该做的循环 3.改变标志，唤醒
 */
class PrintTest{
    private volatile boolean flag=false;//次线程是等待，则主线程不等待
    //主线程调用的方法
    public synchronized void pMain(){
        while (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<20;i++){
            System.out.println("pMain:"+i);
        }
        flag=false;
        this.notify();

    }

    //次线程调用的方法
    public synchronized  void sub(){

        while (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<10;i++){
            System.out.println("sub:"+i);
        }
        flag=true;
        this.notify();

    }

}

/**
 * 通过ReentrantLock和Condition实现
 * 步骤1：判断是否锁住 2.执行该做的循环 3.改变标志，唤醒
 * 优化：可以扩大try的范围，最后在finaly中lock.unlock()
 */
class PrintTest2{
    private volatile boolean flag=false;//次线程是等待，则主线程不等待
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    //主线程调用的方法
    public  void pMain(){
            try {
                lock.lock();
                while (!flag) {
                    condition.await();
                }
                for(int i=0;i<20;i++){
                    System.out.println("pMain:"+i);
                }
                flag=false;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
            finally {
                lock.unlock();
            }

    }

    //次线程调用的方法
    public  void sub(){

        try {
            lock.lock();
            while (flag) {
                condition.await();
            }
            for(int i=0;i<10;i++){
                System.out.println("sub:"+i);
            }
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
