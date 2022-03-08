package com.gan.java8.basis.ThreadTest.simple;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author Badribbit
 * @create 2019/6/11 9:02
 * @Define 有N张火车票，每张票都有一个编号 同时有10个窗口对外售票
 * @Tutorials
 * @Opinion 1.该程序肯定有问题，多线程环境下，remove（）不是安全的，会出现多个线程移除同一个，或者在移除以前已经被移除了，导致该线程没得移除。
 *          2.如果将list换成线程安全的vector，也不行，因为while条件不是安全的，虽然不会出现多个线程移除同一个，但是会出现该线程没得移除。
 *          3.通过加sychronized(tickets),可以解决问题，但是效率低下。
 *          4.使用ConcurrentQueue提高并发性,如果s为null直接退出呗。poll()方法是安全的。
 */
public class TicketSeller2 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();
    static {
        for (int i=0;i<100;i++){
            tickets.add("票号:"+i);
        }
    }
    public static void main(String[] args) {
          for (int i=0;i<10;i++){
              new Thread( ()->{
                  while (true) {
                      String s = tickets.poll();
                      if (s == null) {
                          break;
                      } else {
                          System.out.println("出售了票，" + s);
                      }
                  }
              } ).start();
          }
    }
}
