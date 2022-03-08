package com.gan.java8.basis.Algorithm.simple.test;

/**
 * @Author Badribbit
 * @create 2019/8/28 20:28
 * @Define
 * @Tutorials
 * @Opinion
 */
public class selectSort {

    public static void main(String[] args) {

        int[] arr={1,8,2,9,4,3,6,7,2};
        selSort(arr);
        for(int a: arr){
            System.out.println(a);
        }
    }

    public static void selSort(int [] array){

        for (int i=0;i<array.length-1;i++){
            int k=i;
            for (int j=i+1;j<array.length;j++){
                if (array[j] < array[k]){
                    k=j;
                }
            }
            int temp=array[i];
            array[i]=array[k];
            array[k]=temp;
        }
    }
}
