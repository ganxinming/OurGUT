package com.gan.java8.basis.Algorithm.simple;

/**
 * @Author Badribbit
 * @create 2019/8/3 16:09
 * @Define
 * @Tutorials
 * @Opinion
 */
public class NumberTransfer {
    public static void main(String[] args) {
        //保留6位，并且四舍五入
        String num=String.format("%.6f",(double)2/3);
        System.out.println(Double.parseDouble(num));

        //Math中的舍入
        //向上舍入,只能取整
        System.out.println(Math.ceil(3.29));
        //向下舍入,只能取整
        System.out.println(Math.floor(3.29));
        //标准四舍五入，只能取整
        System.out.println(Math.round(3.59));
    }
}
