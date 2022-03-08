package com.gan.java8.basis.Algorithm.simple;

/**
 * 快排:取第一个数作为中间值，将比他大的放在右边，比他小的放左边，相等的左右都行
 * 难点就是panation方法对一组数据进行排序,
 */
public class QuickSort {
    public static void main(String[] args) {
        int [] a={88,79,92,15,64,31,54,96,71};
        //因为这里我传入的都是位置，所以length-1
        quick(a,0,a.length-1);
        traverse(a);
    }

    public static int panation(int[] arry,int start,int end){
        //将第一个数取出来，作为分割点，左边为小，右边为大
        //其实在我们把这个数取出来的时候，这个位置我们就已经把他当做空了。为后面第一个比分割点小的数作为交换位置了。
        int key=arry[start];
        while(start<end) {
            //先从右边开始向左比较
            while(end>start && arry[end]>=key) {
                //如果都是大的数，而且坐标也是大的，就继续向后比
                end--;
            }
            //一旦发现小的的数，就跟开始预留的空位置进行交换，之后从左边开始向前比
            arry[start]=arry[end];
            //再从左边开始向右
            while (start<end && arry[start] <= key) {
                //如果都是小的数，而且坐标也是小的，就继续向前比
                start++;
            }
            arry[end]=arry[start];

        }
        //最后是用start还是end返回都一样，因为上面的所有while条件是start<end,所以最后肯定是start=end
        arry[start]=key;
        //返回分割点最后的坐标
        return start;

    }
    //递归调用
    public static void quick(int[] arry,int start,int end){
         if (start >= end) {
             return;
         }
         int p=panation(arry,start,end);
         quick(arry,start,p-1);
         quick(arry,p+1,end);
    }

    //遍历数组
    public static void traverse(int[] arry){
        for (int a : arry){
            System.out.println(a);
        }
    }
}
