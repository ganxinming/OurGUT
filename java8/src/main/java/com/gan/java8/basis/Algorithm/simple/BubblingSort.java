package com.gan.java8.basis.Algorithm.simple;

/**
 * 冒泡排序：将最大的向上移动
 * 重点思考：两层循环含义：1 -比较的次数，10个数，需要比较9次，所以length-1
 *                       2.length-1是因为，下面有arry[j+1]，然后由于第一层循环，将最大的数送上了顶，所以第二次比较的长度就-i
 *                       所以是size-1-1;如果发现大的的交换
 */
public class BubblingSort {

    public static void main(String[] args) {
        int[] arr={1,8,2,9,4,3,6,7,2};
        traverse(bubblSort(arr));
    }
    //将大数送到最上面
    public static int [] bubblSort(int[] arry){
        int size=arry.length;
        int temp;
        for (int i=0;i<size-1;i++){
            for (int j=0;j<size-1-i;j++){
                if (arry[j] > arry[j+1]){
                    temp=arry[j];
                    arry[j]=arry[j+1];
                    arry[j+1]=temp;
                }
            }

        }
        return  arry;

    }
    //遍历数组
    public static void traverse(int[] arry){
        for (int a : arry){
            System.out.println(a);
        }
    }
}
