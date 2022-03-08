package com.gan.java8.basis.collectionAndmap;

public interface MyList<E> {
     public int size() ;
     public void add(E e) ;
     public E get (int index);
     public int indexOf(E e);
     public boolean contains(E e);
     public boolean replace(int i,E e);

}
