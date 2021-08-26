package com.ourspring.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author ganxinming
 * @createDate 2021/8/22
 * @description
 */
public class TestMain {
	public static void main(String[] args) {

		gc4();
		System.out.println("我运行完了。。。");
	}

	/**
	 * 手动设置为null，不会被垃圾回收，没有执行finalize
	 *
	 */
	public static void gc1(){
		GanReference reference=new GanReference();
		reference=null;
	}

	/**
	 * 进行System.gc()，将赋值为null对象进行回收了，执行了finalize
	 * 在内存充足的情况下，除非你显式调用 System.gc()，否则它不会进行垃圾回收
	 */
	public static void gc2(){
		GanReference reference=new GanReference();
		reference=null;
		System.gc();
	}

	/**
	 * 执行了finalize
	 * 无需手动回收，触发垃圾回收也会回收为null对象
	 */
	public static void gc3(){
		GanReference reference=new GanReference();
		reference=null;
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
	}

	/**
	 * 发生gc，对象不手动为null，不会释放
	 */
	public static void gc4(){
		GanReference reference=new GanReference();
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
	}

	/**
	 * 软引用
	 * 没有执行finalize方法，因为没有达到内存不足，OOM之前回收
	 * 在内存不足时，没有执行finalize 方法,软引用被终止。当软引用被禁止时，下面的代码等价于下面的伪代码：
	 * if(JVM.内存不足()) {
	 *     softReference = null;
	 *     System.gc();
	 * }
	 */
	public static void gcSoft(){
		SoftReference softReference=new SoftReference<>(new GanReference());
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
	}

	/**
	 * 弱引用
	 *下一次GC前回收，执行了finalize
	 */
	public static void gcWeak(){
		WeakReference weakReference=new WeakReference<>(new GanReference());
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
		ReferenceTest.createOOM();
	}

	/**
	 * 虚引用
	 * 执行了finalize()
	 * 一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来获取一个对象的实例。
	 * 在实例化后，就被终止了，等价于（没啥软用，实例化就被赋为null了）
	 * GanReference ref = new GanReference();
	 * ref = null;
	 *
	 */
	public static void gcPhantom(){
		ReferenceQueue queue = new ReferenceQueue();
		PhantomReference ref = new PhantomReference(new GanReference(), queue);
		System.out.println(ref.get());
		System.gc();
	}

}
