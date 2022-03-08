package com.gan.java8.basis.Exercise;
/**
 * 题目1
 小明的女朋友最喜欢在网上买买买了，可是钱包里钞票有限，不能想买啥就买啥。面对琳琅满目的物品，她想买尽可能多的种类，每种只买一件，同时总价格还不能超过预算上限。于是她请小明写程序帮她找出应该买哪些物品，并算出这些物品的总价格。

 输入规范：
 每个输入包含两行。第一行是预算上限。第二行是用空格分隔的一组数字，代表每种物品的价格。所有数字都为正整数并且不会超过10000。

 输出规范：
 对每个输入，输出应买物品的总价格。

 输入示例1:
 100
 50 50
 输出示例1:
 100

 输入示例2:
 188
 50 42 9 15 105 63 14 30
 输出示例2:
 160
 ---------------------
 作者：March@dhyin.top
 来源：CSDN
 原文：https://blog.csdn.net/strivenoend/article/details/83038137
 版权声明：本文为博主原创文章，转载请附上博文链接！
 */

import java.util.*;

public class Shoping {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int totalprice;
        int[] input;
        while (scanner.hasNext()){
            String total=scanner.nextLine();
            String price=scanner.nextLine();
            String [] totalS=total.split("\\s");
            String [] priceS=price.split("\\s");
            totalprice=Integer.parseInt(totalS[0]);
            input=new int[priceS.length];
            for (int i=0;i<priceS.length;i++){
                input[i]= Integer.parseInt(priceS[i]);
            }
            System.out.println(shop(totalprice,input));
        }
    }

    public static int shop(int total,int[] input){
        int sum=0;
        sort(input);
        for (int a:input){
            sum+=a;
            if (sum >total){
                return sum-a;
            }
            if (sum == total){
                return sum;
            }
        }
        return  0;
    }

    public static void sort(int [] a){
        int size=a.length;
        for (int i=0;i<size-1;i++){
            for (int j=0;j<size-i-1;j++){
                int temp;
                if (a[j]>a[j+1]){
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }

    }
}
