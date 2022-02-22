package com.ourgut.design.pattern.structural.Flyweight;

/**
 * @author ganxinming
 * @createDate 2022/2/11
 * @description
 * 享元模式，主要在于共享通用对象，减少内存的使用，提升系统的访问效率。
 * 而这部分共享对象通常比较耗费内存或者需要查询大量接口或者使用数据库资源，因此统一抽离作为共享对象使用。
 * 应用：数据库连接池，多线程线程池的使用。Integer等包装类，128～127的数据在int范围内是使用最频繁的，为了减少频繁创建对象带来的内存消耗，这里其实是用到了享元模式，以提高空间和时间性能。
 *
 * 享元模式 是对象池的一种实现。类似于线程池，线程池可以避免不停的创建和销毁多个对象，消耗性能。享元模式 也是为了减少内存的使用，避免出现大量重复的创建销毁对象的场景。
 *
 * 享元模式 的宗旨是共享细粒度对象，将多个对同一对象的访问集中起来，不必为每个访问者创建一个单独的对象，以此来降低内存的消耗。
 *
 * 享元模式 把一个对象的状态分成内部状态和外部状态，内部状态即是不变的，外部状态是变化的；然后通过共享不变的部分，达到减少对象数量并节约内存的目的。
 *
 * 享元模式 本质：缓存共享对象，降低内存消耗
 *
 * 使用场景：系统中存在大量的相似对象，需要缓冲池的场景
 *
 * 主要解决
 * 当系统中多处需要同一组信息时，可以把这些信息封装到一个对象中，然后对该对象进行缓存，
 * 这样，一个对象就可以提供给多处需要使用的地方，避免大量同一对象的多次创建，消耗大量内存空间。
 *
 * 享元模式 其实就是 工厂模式 的一个改进机制，享元模式 同样要求创建一个或一组对象，
 * 并且就是通过工厂方法生成对象的，只不过 享元模式 中为工厂方法增加了缓存这一功能。
 *
 *
 * 总结：其实就是对于经常用到的对象，一次性创建多个并进行缓存。每次取的时候从缓存里取
 *
 *
 * 在JDK中，这样的应用不止int，以下包装类型也都应用了享元模式，对数值做了缓存，只是缓存的范围不一样，具体如下表所示：
 *
 * 基本类型	大小	最小值	最大值	包装器类型	缓存范围	是否支持自定义
 * boolean	-	-	-	Bloolean	-	-
 * char	6bit	Unicode 0	Unic ode 2(16)-1	Character	0~127	否
 * byte	8bit	-128	+127	Byte	-128~127	否
 * short	16bit	-2(15)	2(15)-1	Short	-128~127	否
 * int	32bit	-2(31)	2(31)-1	Integer	-128~127	支持
 * long	64bit	-2(63)	2(63)-1	Long	-128~127	否
 * float	32bit	IEEE754	IEEE754	Float	-
 * double	64bit	IEEE754	IEEE754	Double	-
 * void	-	-	-	Void	-	-
 *
 *
 * public class ConnectionPool {
 *
 *     private Vector<Connection> pool;
 *
 *     private String url = "jdbc:mysql://localhost:3306/test";
 *     private String username = "root";
 *     private String password = "root";
 *     private String driverClassName = "com.mysql.jdbc.Driver";
 *     private int poolSize = 100;
 *
 * 重复利用对象
 * public ConnectionPool() {
 *
 *         pool = new Vector<Connection>(poolSize);
 *
 *         try{
 *             Class.forName(driverClassName);
 *             for (int i = 0; i < poolSize; i++) {
 *                 Connection conn = DriverManager.getConnection(url,username,password);
 *                 pool.add(conn);
 *             }
 *         }catch (Exception e){
 *             e.printStackTrace();
 *         }
 *
 *     }
 *
 *     public synchronized Connection getConnection(){
 *         if(pool.size() > 0){
 *             Connection conn = pool.get(0);
 *             pool.remove(conn);
 *             return conn;
 *         }
 *         return null;
 *     }
 *
 *     public synchronized void release(Connection conn){
 *         pool.add(conn);
 * }
 *
 * }
 */
public class Test {
}
