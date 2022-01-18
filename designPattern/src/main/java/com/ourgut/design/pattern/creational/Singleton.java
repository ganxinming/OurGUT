package com.ourgut.design.pattern.creational;

import java.lang.reflect.Constructor;

/**
 * @author ganxinming
 * @createDate 2022/1/11
 * @description
 */
public class Singleton {

	private Singleton(){
		/**
		 * 枚举类 可以通过抛异常去阻止反射初始化
		 */
//		if(LazyHolder.INSTANCE != null){
//			throw new RuntimeException("不允许创建多个实例");
//		}
	}

	/**
	 * 以下实例，忽略staic class，因为都有用到静态变量，所以必须定义成static
	 */
	/**
	 * 饿汉模式：就是所有对象类在加载的时候就实例化。这样一来，如果系统中有大批量的单例对象存在，而且单例对象的数量也不确定，
	 * 则系统初始化时会造成大量的内存浪费(造成内存浪费)
	 * 缺点：内存浪费
	 */
	static class HungryStaticSingleton{
		private static HungryStaticSingleton hungryStaticSingleton;
		static {
			hungryStaticSingleton=new HungryStaticSingleton();
		}
		public static HungryStaticSingleton getInstance(){
			return hungryStaticSingleton;
		}
	}

	/**
	 * 懒汉模式：只有在调用时才加载，有线程安全问题,可能出现两个线程同时创建不同单例
	 * 缺点：线程不安全
	 */
	static class  LazySimpleSingleton{
		private static LazySimpleSingleton lazySimpleSingleton;

		private static LazySimpleSingleton getInstance(){
			if(lazySimpleSingleton == null){
				lazySimpleSingleton=new LazySimpleSingleton();
			}
			return lazySimpleSingleton;
		}
	}
	/**
	 * DLC双重检查锁模式,流程：1.判空2.加锁3.判空4.初始化对象
	 * 缺点：用了锁，有性能问题(其实还好，可以忽略不计)
	 */
	static class LazyDoubleCheckSingleton{
		private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton;

		private static LazyDoubleCheckSingleton getInstance(){
			/**
			 * 为什么要先判空，后加锁？如果先加锁，则所有对象都会阻塞在这，降低性能
			 */
			if(lazyDoubleCheckSingleton == null){
				/**
				 * 加锁，保证执行初始化的线程，能按顺序去执行(对，只是保证线程能按顺序执行，不能保证只有一个线程创建初始化)
				 */
				synchronized (LazyDoubleCheckSingleton.class){
					/**
					 * 因为上面的加锁也只是保证线程顺序执行，所以还需判断一次，保证不能执行两次
					 */
					if(lazyDoubleCheckSingleton == null){
						lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
					}
				}
			}
			return null;
		}
	}

	/**
	 * 静态内部类，在内部类里，直接可以new出来，反正不会加载，只有在调用时才会执行
	 * //利用了Java本身的语法特点，默认不加载内部类
	 * 感悟：感觉和饿汉一样，只不过换成了静态内部类去new对象
	 * 缺点：容易被反射攻击，序列化和反序列化攻击，可以在构造方法中去逻辑优化，但是还是不够优雅
	 */
	private static class LazyHolder{
		private static Singleton INSTANCE=new Singleton();
	}
	public static Singleton getSingleton(){
		return LazyHolder.INSTANCE;
	}


	/**
	 * 枚举创建：
	 * 缺点：
	 */
	//定义一个静态枚举类
	static enum SingletonEnum{
		//创建一个枚举对象，该对象天生为单例
		INSTANCE;
		private Singleton singleton;
		//私有化枚举的构造函数
		private SingletonEnum(){
			singleton=new Singleton();
		}
		public Singleton getInstance(){
			return singleton;
		}
	}

	public static Singleton getInstance(){
		return SingletonEnum.INSTANCE.getInstance();
	}

	/**
	 * 防止原型模式进行克隆时破坏单例
	 * @return
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
	}


	public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException {
		/**
		 * 静态内部类初始化
		 */
		Singleton singleton = Singleton.getSingleton();
		System.out.println(singleton);
		Singleton singleton1 = Singleton.getSingleton();
		System.out.println(singleton1);
		/**
		 * 使用反射攻击,拿到Class就可以初始化了
		 */
		Class<Singleton> singletonClass = Singleton.class;
		//通过反射获取私有的构造方法
		Constructor<Singleton> declaredConstructor = singletonClass.getDeclaredConstructor(null);
		//强制访问
		declaredConstructor.setAccessible(true);
		//暴力初始化，可以通过注释的构造方法去抵制反射攻击
		Singleton singleton2 = singletonClass.newInstance();
		System.out.println(singleton2);
	}
}
