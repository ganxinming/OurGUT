package com.gan.java8.basis.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author 12714
 * Date类，Calendar类
 */
public class TestDate {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(date.getTime());
        //距离格林尼治总共时间
        System.out.println(date.toInstant());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(ft.format(date));
       long j= System.currentTimeMillis();
        System.out.println(j);
        //Calendar只能这样getInstance创建，他类似一个语言环境
        Calendar cld=Calendar.getInstance();
        cld.set(2018,1,21);
        System.out.println(cld.getTime());
        //GregorianCalendar类是Calendar的一个实现类
        GregorianCalendar gg=new GregorianCalendar();
        System.out.println(gg.getTime());
        //将字符串转成相应格式的字符串
       SimpleDateFormat ftt=new SimpleDateFormat("yyyy/MM/dd");
        String str = "2016/1/5 12:12:12";
        try {
            Date ddd = ftt.parse(str);
            System.out.println(ddd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
/*
    Date没什么特别的，获得当前时间
    simpleDateFormat用于设置输出的格式搭配format使用
    其中Date的gettime和System.currentTimeMillis一样都是返回秒数
    Calendar比Date类强大的多，可以自由设定日期，并且可以自由加减。方法可以可以去查API
 */
