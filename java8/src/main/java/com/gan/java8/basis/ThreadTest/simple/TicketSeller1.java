package com.gan.java8.basis.ThreadTest.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Badribbit
 * @create 2019/6/11 9:02
 * @Define 有N张火车票，每张票都有一个编号 同时有10个窗口对外售票
 * @Tutorials
 * @Opinion 1.该程序肯定有问题，多线程环境下，remove（）不是安全的，会出现多个线程移除同一个，或者在移除以前已经被移除了，导致该线程没得移除。
 *          2.如果将list换成线程安全的vector，也不行，因为while条件不是安全的，虽然不会出现多个线程移除同一个，但是会出现该线程没得移除。
 *          3.通过加sychronized(tickets),可以解决问题，但是效率低下。
 */
public class TicketSeller1 {
    static List<String> tickets = new ArrayList<>();
    static {
        for (int i=0;i<100;i++){
            tickets.add("票号:"+i);
        }
    }
    static  List<String> result = new ArrayList<>();
    static AtomicInteger integer=new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
          for (int i=0;i<10;i++){
			  int finalI = i;
			  new Thread( ()->{
                  while (tickets.size()>0){
//                      synchronized (tickets){
					  String remove = tickets.remove(0);
					  System.out.println("窗口:"+ finalI +"出售了票，" + remove);
//					  }
					  integer.incrementAndGet();
					  result.add(remove);
                      }

              } ).start();
          }
          Thread.sleep(2000);
		System.out.println(integer.get());
		System.out.println(result);
    }
}
