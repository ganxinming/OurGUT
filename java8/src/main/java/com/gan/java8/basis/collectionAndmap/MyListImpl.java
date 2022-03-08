package com.gan.java8.basis.collectionAndmap;

import java.util.Arrays;

public class MyListImpl<E> implements MyList<E> {
    private final static int DEFAULT_MIXSIZE=10;
    private final static int DEFAULT_MAXSIZE=200;
    //默认长度
    private Object [] elementData;
    //定义数组
    private int size;
    /**
     * 默认无参构造，默认初始化10数组
     */
    //实际长度
    public MyListImpl() {
        this(DEFAULT_MIXSIZE);
    }
    public MyListImpl(int length) {
        if (length<0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData=new Object[length];
    }
    public void CheckCapacityAndGrows(int size){
        if (size <0 || size > DEFAULT_MAXSIZE){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int oldCapacity=elementData.length;
        if (size>oldCapacity){
            int newCapacity=oldCapacity+(oldCapacity>>1);
            if (newCapacity<DEFAULT_MIXSIZE){
                newCapacity=DEFAULT_MIXSIZE;
            }
            if (newCapacity>DEFAULT_MAXSIZE){
                newCapacity=DEFAULT_MAXSIZE;
            }
            elementData= Arrays.copyOf(elementData,newCapacity);
        }

    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        CheckCapacityAndGrows(size+1);
        elementData[size++]=e;
    }

    @Override
    public E get(int index) {
        return (E) elementData[index];
    }

    @Override
    public int indexOf(E e) {
        for (int i=0;i<elementData.length;i++){
            if (e==elementData[i]){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E e) {
        for (int i=0;i<elementData.length;i++){
            if (e == elementData[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replace(int i, E e) {
        if (i>=0 && i<elementData.length){
            elementData[i]=e;
            return  true;
        }
        return false;
    }
    public boolean remove(E e){
        for (int i=0;i<elementData.length;i++){
            if (e == elementData[i]){
                remove(i);
                return true;
            }

        }
        return  false;
    }
    public E remove(int index){
        E e=get(index);
        System.arraycopy(elementData,index+1,elementData,index,size-index-1);
        elementData[size--]=null;
        //这一步太帅了，吹爆
        return e;
    }
}
