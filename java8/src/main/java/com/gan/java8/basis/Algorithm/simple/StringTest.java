package com.gan.java8.basis.Algorithm.simple;

/**
 * @Author Badribbit
 * @create 2019/8/4 11:35
 * @Define
 * @Tutorials
 * @Opinion
 */
public class StringTest {
    public static void main(String[] args) {
        //字符串分割
        String ss=new String("abcd");
        //[0,2)
        System.out.println(ss.substring(0,2));
        //[2,lengh)
        System.out.println(ss.substring(2,ss.length()));

        //将字符串按某个值分割成数组
        String st=new String("1 2 333 44");
        //以空格分开
        String[] spilt=st.split("//s");
        for(int i=0;i<spilt.length;i++){
            System.out.print(spilt[i]);
            System.out.println();
        }

        //利用分割变成数组
        String aa=new String("[1,2,3,4]");
        String bb=aa.substring(1,aa.length()-1);
        String[] cc=bb.split(",");
        int [] val=new int[cc.length];
        for (int i =0;i<cc.length;i++){
            val[i]=Integer.parseInt(cc[i]);
            System.out.println(val[i]);
        }

    }

}
