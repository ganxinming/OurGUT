package com.gan.java8.basis.number;

/**
 * @author 12714
 * 对八大类型的理解
 */
public class Bcsilfdb {
    public static void main(String[] args) {
        byte b=127;
        Byte by=Byte.valueOf(b);
        Byte by1=new Byte("127");
        boolean  bool=Boolean.FALSE;
        Boolean boo=new Boolean("true");
        System.out.println(""+by1+boo);
        char ch='\uffff';
        System.out.println(ch);
        long i=1232321323232142L;
        float j=12332132112312223432432432432F;
        double k=12321324234324D;
        System.out.println(j);
    }
}
/*
其中valueof属于类的方法。可以通过类.方法调用，将他们转成相应的类型。
 */