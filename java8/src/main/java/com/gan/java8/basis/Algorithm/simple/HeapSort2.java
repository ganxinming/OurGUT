package com.gan.java8.basis.Algorithm.simple;

/**
 * @Author Badribbit
 * @create 2019/6/24 8:49
 * @Define 其实这个堆排序就是对所有父节点开始排序。选出子节点最大值与父节点比较。
 * 如果子节点大于父节点则替换，将替换后的子节点作为父节点继续
 * 和他的子节点比较.
 * @Tutorials
 * @Opinion
 */
//public class HeapSort2 {
//    public static void main(String[] args) {
//        int [] a={19,82,38,47,57,10,94,88,45,23,45,23,1,2,5,6,9,3};
//        HeapSort2 s=new HeapSort2();
//        s.heapSort(a);
//        traverse(a);
//    }
//
//    public void heapSort(int [] arry){
//
//        for (int i=0;i<arry.length;i++){
//            adjustHeap(arry,arry.length-i-1);
//            swap(arry,0,arry.length-i-1);
//        }
//    }
//    public void adjustHeap(int [] arry,int length){
//
//        int chrilden=(length-1)/2;
//        for (int i=chrilden;i>0;i--){
//            int p=i*2+1;
//            int max=arry[p];
//            //判断有没有右节点
//            if (p < length){
//                //有右节点，比较出最大值
//                max=arry[p+1] <
//            }
//
//
//        }
//    }
//    public void swap(int [] arry,int start,int end){
//        arry[start]=arry[start]^arry[end];
//        arry[end]=arry[start]^arry[end];
//        arry[start]=arry[start]^arry[end];
//    }
//
//    //遍历数组
//    public static void traverse(int[] arry){
//        for (int a : arry){
//            System.out.println(a);
//        }
//    }
//}
