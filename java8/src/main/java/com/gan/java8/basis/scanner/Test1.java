package com.gan.java8.basis.scanner;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n <=0 || m <=0){
                System.out.println("请输入正确的值");
            }
            else {
                int sum = sum(n, m);
                System.out.println("总共需要" + sum);
            }
        }
    }
    public static int sum(int n,int m){
        if (n < m){
            int i=2;
            while (n*i < m){
                ++i;
            }
            if (n*i == m) {
                return i;
            }
            else {
                return i ;
            }
        }
        else {
            return 1;
        }
    }
}
