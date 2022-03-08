package com.gan.java8.basis.ThreadTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author Badribbit
 * @create 2019/6/8 20:04
 * @Define 创建线程的四种方式
 * @Tutorials
 * @Opinion:
 *
 * 对于实现Callable接口通过FutureTask包装器来创建Thread线程
 * 1.实现Callable接口2.创建FutureTask（他实现了runnable）3.实例化化Thread
 * 对于使用ExecutorService、Callable、Future实现有返回结果的线程
 * 1.实现Callable接口2.通过在main方法中的线程池的submit方法执行。3.得到future对象（返回的对象）
 * 重要：1.Runnable接口，需要实例化Thread才能调用start方法。
 *      2.线程池两种执行线程方法,execute()和submit().
 *      execute()主要针对的Runnable的类，他没有返回值
 *      submit() 主要针对的Callable的类，他有返回值，且返回值用future定义。
 *      3.future通过get()获得返回值。
 */
public class SimpleTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Thread 方式实现
        St1 s1=new St1();
        s1.start();
        //Runnable 方式实现
        St2 s2=new St2();
        Thread ts2=new Thread(s2);
        ts2.start();
        //实现Callable接口通过FutureTask包装器来创建Thread线程
        //由Callable<Integer>创建一个FutureTask<Integer>对象：
        Callable callable=new SomeCallable();
        //注释：FutureTask<Integer>是一个包装器，它通过接受Callable<Integer>来创建，它同时实现了Future和Runnable接口
        FutureTask futureTask=new FutureTask(callable);
        Thread s3=new Thread(futureTask);
        s3.start();
        //使用ExecutorService、Callable、Future实现有返回结果的线程
        //创建一个线程池,返回一个ExecutorService。
        ExecutorService pool= Executors.newFixedThreadPool(4);
        //Future就是Callable的call方法返回的封装了结果的对象
        List<Future> list=new ArrayList<Future>();
        for (int i=0;i<9;i++){
            Callable callable1=new OtherCallable(""+i);
            //submit提交Callable，执行并有返回值,用future接受
            Future future=pool.submit(callable1);
            list.add(future);
        }
        //关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
       for (int i=0;i<9;i++){
           Future future=list.get(i);
           //future通过get方法，取返回的对象。
           System.out.println(future.get().toString());
       }
    }
}

class St1 extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("通过继承Thread方式运行");
    }
}

/**
 * 因为Runnable没有start方法，需要Thread ts2=new Thread(s2);将它变成Thread类。
 */
class St2 implements Runnable{
    @Override
    public void run() {
        System.out.println("通过实现Runnable接口");
    }
}

/**
 * 实现Callable接口通过FutureTask包装器来创建Thread线程
 * @param
 */
class SomeCallable implements Callable{

    @Override
    public Object call() throws Exception {
        System.out.println("实现Callable接口通过FutureTask包装器来创建Thread线程");
        return null;
    }
}

/**
 * 使用ExecutorService、Callable、Future实现有返回结果的线程
 */
class OtherCallable implements Callable{
    private String name;
    public OtherCallable(String name){
        this.name=name;
    }
    @Override
    public Object call() throws Exception {
        Date date=new Date();
        System.out.println("线程名"+name);
        System.out.println("开始运行时间"+date.toString());
        return  date;
    }
}



