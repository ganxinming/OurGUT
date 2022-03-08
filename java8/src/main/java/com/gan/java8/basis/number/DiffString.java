package com.gan.java8.basis.number;

/**
 * @author 12714
 * String,StringBuffer,StringBuilder的不同
 */
public class DiffString {
    public static void main(String[] args) {
        String a="java我爱你";
        String b=new String("java我爱你");
        System.out.println(a==b);
        //一个是对象 一个是常量。比较肯定是false
        b=String.format("213%s","实验");
        System.out.printf("232%s",a);
        System.out.println(b);
        StringBuffer ss=new StringBuffer();
    }
}
/*
String 不可变，里面是final修饰
new出来的String在堆区(默认是"")，直接写出来的在常量池(默认是null,因为是引用)。
format和printf差不多都是用通配符填充，但是format只是填充，printf打印出来了。
StringBuffer是安全的，StringBuilder是不安全的。
 */