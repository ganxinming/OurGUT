package com.gan.java8.basis.Algorithm.simple.test;

/**
 * @Author Badribbit
 * @create 2019/8/28 21:07
 * @Define
 * @Tutorials
 * @Opinion
 */
public class quickSort {
    public static void main(String[] args) {

        int[] arr={1,8,2,9,4,3,6,7,2};
        sort(arr,0,arr.length-1);
        for(int a: arr){
            System.out.println(a);
        }
    }
    public static void sort(int [] array,int left,int right){
        if (left >= right){
            return;
        }
        int mid=quick(array,left,right);
        sort(array,left,mid-1);
        sort(array,mid+1,right);
    }
    public static int quick(int [] array,int left,int right){

        int value=array[left];
        while(left < right){
            //1.忘记判断left < right条件 2.忘记>=条件
            while (left < right &&array[right] >= value){
                right--;
            }
            array[left]=array[right];
            while (left < right && array[left] <= value){
                left++;
            }
            array[right]=array[left];
        }
        array[right]=value;
        return right;
    }
}
