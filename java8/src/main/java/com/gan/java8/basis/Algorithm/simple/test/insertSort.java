package com.gan.java8.basis.Algorithm.simple.test;

/**
 * @Author Badribbit
 * @create 2019/8/28 20:45
 * @Define
 * @Tutorials
 * @Opinion
 */
public class insertSort {
    public static void main(String[] args) {

        int[] arr={1,8,2,9,4,3,6,7,2};
        insetSort(arr);
        for(int a: arr){
            System.out.println(a);
        }
    }
    public static void insetSort(int[] array){

        for (int i=0;i<array.length;i++){
            int guard=array[i];
            int j=i-1;
            //1.忘记条件j >=0  2.j=i-1和j=i，该怎么选择，那么下面的条件也会变j >=0和j>0,需要时可以自己代值试下
            while (j >=0 && array[j]> guard){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=guard;
        }

    }
}
