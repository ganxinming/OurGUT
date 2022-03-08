package com.gan.java8.basis.Algorithm.simple.test;

/**
 * @Author Badribbit
 * @create 2019/8/28 19:29
 * @Define
 * @Tutorials 冒泡排序
 * @Opinion
 */
public class BubblingSort {
    public static void main(String[] args) {

        int[] arr={1,8,2,9,4,3,6,7,2};
        bubSort(arr);
        for(int a: arr){
            System.out.println(a);
        }
    }

    public static void bubSort(int[] arry){

        for (int i=0;i<arry.length-1;i++){
            for (int j=0;j<arry.length-1-i;j++){
                if (arry[j] > arry[j+1]){
                    int temp=arry[j];
                    arry[j]=arry[j+1];
                    arry[j+1]=temp;
                }
            }
        }

    }

}
