package com.gan.java8.basis.number;

/**
 * @author 12714
 * 局部变量，类变量，成员变量
 */
public class Variable {
    int t=20;
    static int k=128;
    public void play(int t){
        System.out.println(t);
        t=111;
        System.out.println(t);
    }
    public static void main(String[] args) {
        Variable v=new Variable();

    }
}
