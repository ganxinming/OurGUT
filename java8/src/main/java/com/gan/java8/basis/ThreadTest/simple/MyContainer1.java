package com.gan.java8.basis.ThreadTest.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Badribbit
 * @create 2019/6/10 21:24
 * @Define * 曾经的面试题：（淘宝？）
 *  * 实现一个容器，提供两个方法，add，size
 *  * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * @Tutorials
 * @Opinion
 */
public class MyContainer1 extends Thread{
    private volatile boolean  flag=false;
    volatile List lists = new ArrayList();
    class MyContainer2 extends  Thread{
        @Override
        public void run() {
            for (int i=0;i<10;i++){
                if (!flag) {
                    add(i);
                    System.out.println("add:" + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    break;
                }
            }
        }
    }
    public void add(Object o) {
        lists.add(o);

    }
    public int size() {
        return lists.size();
    }
    @Override
    public void run() {

        while (size() != 5){

        }
        flag=true;
    }
    public static void main(String[] args) {
        MyContainer1 c1 = new MyContainer1();
        MyContainer1.MyContainer2 c2=c1.new MyContainer2();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}