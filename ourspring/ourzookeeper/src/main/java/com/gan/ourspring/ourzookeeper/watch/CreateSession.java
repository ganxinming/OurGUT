package com.gan.ourspring.ourzookeeper.watch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author ganxinming
 * @createDate 2021/3/22
 * @description
 */
/**
 * ZooKeeper 客户端和服务端会话的建立是一个异步的过程，
 * 也就是说在程序中，构造方法会在处理完客户端初始化工作后立即返回，
 * 在大多数情况下，此时并没有真正建立好一个可用的会话，在会话 的生命周期中处于“CONNECTING”的状态。
 * 当该会话真正创建完毕后ZooKeeper服务端会向会话对应的客户端发送一个事件通知，
 * 以告知客户端，客户端只有在获取这个通知之后，才算真正建立了会话。
 */

/**
 * 1、任何操作对节点进行操作的动作都要先判断节点是否存在，不然报错，有点麻烦
 * 2、对于节点不能超过两级目录，不然就报错,不能递归创建节点
 * 所以这个原生的zookeeper一般不使用
 */

/**
 * 对于每个ZNode，Zookeeper都会为其维护 一个叫作Stat的数据结构，Stat记录了这个ZNode的三个数据版本，
 * 分别是version(当前ZNode的版 本)、cversion(当前ZNode子节点的版本)、aversion(当前ZNode的ACL版本)。
 */

/**
 * 为啥要使用zk的节点通知呢？
 * 场景：A机器缓存了数据(提高利用率)，B机器进行了修改，但是A又不知道，
 * 此时需要通知A重新缓存，其实很简单，就是创建一个c节点，A监听c节点的变更，
 * B是造成数据的变更，所以需要对c节点进行更改值，改变后，A监听到后就进行重新缓存。(节点中的值可以是时间戳)
 */
public class CreateSession implements Watcher {

	public static CountDownLatch countDownLatch=new CountDownLatch(1);

	/*
        客户端通过创建一个zk实例来连接zk服务器
        connectString:连接地址:IP:端口
        sessionTimeOut:回话超时时间:单位毫秒
        Watcher:监听器实现Watcher(当特定时间出发监听,zk会通过watcher通知客户端)
     */
	public static void main(String[] args) throws Exception, InterruptedException {
		/**
		 * watcher监听状态
		 * 返回一个zookeeper对象
		 */
		ZooKeeper zooKeeper=new ZooKeeper("115.159.202.204:2181", 500000, new CreateSession());
		System.out.println(zooKeeper.getState());
		countDownLatch.await();

		System.out.println("等待zk连接成功");
		// 表示回话真正建立
		System.out.println("===========>Client Connected to zookeeper");

//		createNodeSync(zooKeeper);
		updateNodeSync(zooKeeper);
		getChildren(zooKeeper);
		deleteNode(zooKeeper);
	}

	/**
	 * 处理来自zk的通知
	 * @param watchedEvent
	 */
	@Override
	public void process(WatchedEvent watchedEvent) {


		if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
			System.out.println("连接成功，解锁");
			countDownLatch.countDown();
		}
		if(watchedEvent.getState() == Event.KeeperState.Disconnected){
			System.out.println("解除连接");
		}
	}


	/**
	 * 增加节点
	 * @param zooKeeper
	 * @throws Exception
	 */
	public static void createNodeSync(ZooKeeper zooKeeper) throws Exception {

		System.out.println("createNode..");

		if(Objects.isNull(zooKeeper.setData("/test", "更改节点后的值".getBytes("UTF-8"), -1))){
			String s = zooKeeper.create("/test", "持久化节点GAN".getBytes("utf-8"),
					ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}

		/**
		 * path:节点创建的路径
		 * data[]:节点创建要保存的数据，是个byte类型的
		 * acl:节点创建的权限信息(4种类型)
		 ANYONE_ID_UNSAFE : 表示任何人
		 AUTH_IDS :此ID仅可用于设置ACL。它将被客户机验证的ID替换
		 OPEN_ACL_UNSAFE :这是一个完全开放的ACL(常用)--> world:anyone
		 CREATOR_ALL_ACL :此ACL授予创建者身份验证ID的所有权限
		 * createMode:创建节点的类型(4种类型)
		 PERSISTENT:持久节点
		 PERSISTENT_SEQUENTIAL:持久顺序节点
		 EPHEMERAL:临时节点
		 EPHEMERAL_SEQUENTIAL:临时顺序节点
		 String node = zookeeper.create(path,data,acl,createMode);
		 */
		String node_PERSISTENT = zooKeeper.create("/xw_persistent", "持久化节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		String node_PERSISTENT_SEQUENTIAL = zooKeeper.create("/xw_persistent_sequential", "持久化顺序节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		String node_EPHEMERAL = zooKeeper.create("/xw_ephemeral", "临时节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		String node_EPHEMERAL_SEQUENTIAL = zooKeeper.create("/xw_ephemeral_sequential", "临时顺序节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("创建的持久节点是:"+node_PERSISTENT);
		System.out.println("创建的持久顺序节点是:"+node_PERSISTENT_SEQUENTIAL);
		System.out.println("创建的临时节点是:"+node_EPHEMERAL);
		System.out.println("创建的临时顺序节点是:"+node_EPHEMERAL_SEQUENTIAL);
	}

	/**
	 * 更新节点的值
	 * @param zooKeeper
	 * @throws Exception
	 */
	public static void updateNodeSync(ZooKeeper zooKeeper) throws Exception{
		byte[] data = zooKeeper.getData("/test", false, null);
		String s = new String(data, "utf-8");
		Stat stat = zooKeeper.setData("/test", "更改节点后的值".getBytes("UTF-8"), -1);

		if(Objects.nonNull(stat)){
			byte[] data1 = zooKeeper.getData("/test", false, null);
			System.out.println(new String(data1,"UTF-8"));
		}
		else{
			System.out.println("节点不存在");
		}
	}

	/**
	 * 获取子节点
	 * @param zooKeeper
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static void  getChildren(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
		List<String> children = zooKeeper.getChildren("/test", true);
		System.out.println("children: " + children);
	}

	/**
	 * 删除节点
	 * @param zooKeeper
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public static void deleteNode(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
		List<String> children = zooKeeper.getChildren("/test", false);
		Stat exists = zooKeeper.exists("/test", false);
		if(Objects.nonNull(exists)){
			zooKeeper.delete("/test",-1);
			Stat exist = zooKeeper.exists("/test", false);
			System.out.println(exist == null ? "节点被删除" : "节点删除失败");
		}
		System.out.println("children: " + children);
	}



}
