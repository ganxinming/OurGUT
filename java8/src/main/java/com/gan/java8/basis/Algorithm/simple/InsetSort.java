package com.gan.java8.basis.Algorithm.simple;

/**
 * 插入排序 :从前往后，每次将数从以排好的序列插入
 */
public class InsetSort {
    public static void main(String[] args) {
        int[] a={1,9,5,6,7,4,2,3,8,0};
        sort(a);
        traverse(a);
    }
    public static void sort(int [] arry){

        for (int i=0;i<arry.length;i++){
            //将需要排序的值赋给temp，
            int temp=arry[i];
            int j=i;
            //因为前面都是排好序的，所以将temp跟前面的比较，从后向前比较
            //如果小于，就把值向后移，因为arry[i]，这个位置是空的，值已经交给temp了
            while (j >0 && temp < arry[j-1]){
                arry[j]=arry[j-1];
                j--;
            }
            //只要一有发现不在满足temp<arry[j-1]，就直接退出了，因为我们前面那个序列是有序的，只有一不满足就可以退出，也正好插在这个位置
            arry[j]=temp;
        }

    }
    //遍历数组
    public static void traverse(int[] arry){
        for (int a : arry){
            System.out.println(a);
        }
    }
}
