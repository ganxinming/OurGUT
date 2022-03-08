package com.gan.java8.basis.collectionAndmap;

public class TestArrayList {
    public static void main(String[] args) {
        MyListImpl list=new MyListImpl();
        list.add("甘新明");
        list.add(123);
        list.remove(0);
        list.replace(0,"22");
        System.out.println(list.get(0));
    }
}
