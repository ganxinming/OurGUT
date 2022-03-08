package com.gan.java8.basis.Algorithm.simple.test;

/**
 * @Author Badribbit
 * @create 2019/8/28 21:41
 * @Define
 * @Tutorials
 * @Opinion
 */
public class MergeSort {
    public static void main(String[] args) {

        int[] arr={1,8,2,9,4,3,6,7,2};
        mergeSort(arr,0,arr.length-1);
        for(int a: arr){
            System.out.println(a);
        }
    }
    public static void mergeSort(int [] array,int left,int right){
        //忘了,他跟快排一样，条件写外边
        if (left >= right){
            return;
        }
        int mid=(left+right)/2;
        mergeSort(array,left,mid);
        mergeSort(array,mid+1,right);
        //忘记加mid作为两个数组分界
        sort(array,left,mid,right);
    }
    public static void sort(int [] array,int left,int mid,int right){

        int[] temp=new int[right+1];
        int key=left;//遍历
        int start=left;
        int end=mid+1;
        //忘记写==，记得mid是左边数组终点，。mid+1是右边开头
        while (start <=mid && end<=right){
            temp[left++]=array[start]>array[end] ? array[end++] :array[start++];
        }
        while(start <=mid){
            temp[left++]=array[start++];
        }
        while (end <= right){
            temp[left++]=array[end++];
        }

        for (int i=key;i<=right;i++){
            array[i]=temp[i];
        }
    }
}
