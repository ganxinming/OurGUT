package com.gan.java8.basis.Algorithm.simple;

/**
 * 选择排序：每次选出最小的放在最前面
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] a={1,9,3,8,6,4,2,8,0,1,43,6};
        seleSort(a);
        traverse(a);
    }
    public static void seleSort(int arry[]){
        //循环遍历
        for (int i=0;i<arry.length;i++){
            //默认开头i的值最小，用k记录下来，但是k的值在后面是变化的，因为k是最小值的下标
            int k=i;
            //从i+1开始，进行比较，如果比最小值小，就改变k的值，最后，将k下标的值，和i下标的值进行交换.
            for (int j=i+1;j<arry.length;j++){
                if (arry[j]<arry[k]){
                    k=j;
                }
            }
            int temp=arry[k];
            arry[k]=arry[i];
            arry[i]=temp;

        }
    }

    //遍历数组
    public static void traverse(int[] arry){
        for (int a : arry){
            System.out.println(a);
        }
    }
}
