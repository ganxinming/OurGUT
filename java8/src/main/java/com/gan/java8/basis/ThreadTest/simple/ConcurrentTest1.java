package com.gan.java8.basis.ThreadTest.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author Badribbit
 * @create 2019/6/11 21:40
 * @Define ConcurrentSkipListMap和ConcurrentHashMap
 * @Tutorials
 * @Opinion
 * 1.ConcurrentHashMap 高并发下操作安全，里面的任何一个方法都是安全的。
 * 2.ConcurrentSkipListMap 也是安全并且可以自动排序按照插入的顺序排序，所以他的插入时间会长点，需要排序。
 * 3.出现一个问题，如果键值(int)是按顺序插入，那么取出时也是有序的，因为map是数组加链表，这些键直接按
 *   顺序作为索引了，所以取出也是有序。但是如果插入key是随机的，那么也就不是有序的了。
 */
public class ConcurrentTest1 {
    static Map<Integer,Integer> map1=new ConcurrentHashMap();
    static Map<Integer,Integer> map2=new ConcurrentSkipListMap<>();
    static Map<Integer,Integer> map=new HashMap();
    static {
        int i=0;
        while (i < 50){
            int ram=(int)(Math.random()*1000);
            int ram1=(int)(Math.random()*500);
            map.put(ram1,ram);
            map1.put(ram1,ram);
            map2.put(ram1,ram);
            i++;
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> m = new HashMap<>();
        long start1=System.currentTimeMillis();
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            System.out.print("map键："+entry.getKey());
            System.out.println("map值："+entry.getValue());
        }
        for (Map.Entry<Integer,Integer> entry : map1.entrySet()){
            System.out.print("map1键："+entry.getKey());
            System.out.println("map1值："+entry.getValue());
        }
        for (Map.Entry<Integer,Integer> entry : map2.entrySet()){
            System.out.print("map2键："+entry.getKey());
            System.out.println("map2值："+entry.getValue());
        }
        long end1=System.currentTimeMillis();
        System.out.println("消耗时间"+(end1-start1));

    }

}
