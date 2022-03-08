package com.gan.java8.basis.scanner;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum=in.nextInt();
        int[] arr=new int[sum*2];
        for (int i=0;i<arr.length;){
            arr[i]=in.nextInt();
            arr[i+1]=in.nextInt();
            i+=2;
        }
        getSum(arr);
    }
    public static void getSum(int [] arr){
        for (int j=0;j<arr.length;){
            if (arr[j] == arr[j+1]){
                if (arr[j]%2 == 0){
                    System.out.println(arr[j]);
                }
                else {
                    System.out.println(-arr[j]);
                }
            }
            else
            {
                int suml=0;
                int sumr=0;
                if (arr[j]%2 == 0){
                    suml=(arr[j])/2;

                }
                else {
                    suml=-((arr[j])/2+1);
                    arr[j]=-arr[j];
                }
                if (arr[j+1]%2 == 0){
                    sumr=arr[j+1]/2;
                }
                else {
                    sumr=-(arr[j+1]/2+1);
                }

                System.out.println(sumr-suml+arr[j]);
            }
            j+=2;

        }
    }
}
