package com.gan.java8.basis.number;

/**
 * @author 12714
 */
public class Transfer {
    public static void main(String[] args) {
        int i=12;
        String j="123";
        Integer b=Integer.valueOf(j);
        String k=Integer.toString(i);
        int l=Integer.parseInt(j);
        System.out.println(l);
        String g="q";
        char q=g.charAt(0);
        System.out.println(g);
    }
}
/*
parseInt :转成int类型
valueOf :转成Integer
toString：转成字符串
charAt ：转成char
 */