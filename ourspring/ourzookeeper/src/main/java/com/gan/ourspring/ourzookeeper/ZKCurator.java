package com.gan.ourspring.ourzookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ganxinming
 * @createDate 2021/1/17
 * @description
 */
public class ZKCurator {


	private final static String ZK_URL = "115.159.202.204:2181";

	private final static int TIME_OUT = 5000;

	private static ZooKeeper zkClient = null;
	//三个host，分别代表三个节点
	private static final String ADDR = "115.159.202.204:2181";
	private static final String PATH = "/zk_test";

	@Before
	public void init() throws Exception {
		zkClient = new ZooKeeper(ZK_URL, TIME_OUT, (WatchedEvent event) -> {
			// 收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
			System.out.println(event.getType() + "---" + event.getPath());
			try {
				zkClient.getChildren("/", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 设置值
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetData() throws Exception {
		zkClient.setData("/eclipse", "world".getBytes(), -1);
		byte[] data = zkClient.getData("/eclipse", false, null);
		System.out.println(new String(data));
	}

	/**
	 * 创建节点
	 *
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception {
		// 参数1：要创建的节点的路径 参数2：节点数据 参数3：节点的权限 参数4：节点的类型
		zkClient.create("/eclipse/aaa", "aaaData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	public static void main(String[] args) throws InterruptedException {
		final CuratorFramework zkClient = CuratorFrameworkFactory.newClient(ADDR, new RetryNTimes(10, 5000));
		zkClient.start();
		System.out.println(zkClient.checkExists());
		System.out.println("开始创建Zookeeper客户端...");
		try {
			registerWatcher(zkClient);
			//registerNodeCache(zkClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("监听器创建完毕...");
		//直接堵塞当前线程   因为zookeeper那边有独立的线程管理，这边堵塞避免因为前台进程没有了进程被结束
		Thread.sleep(Integer.MAX_VALUE);
		zkClient.close();
	}

	private static void registerWatcher(CuratorFramework zkClient) throws Exception {
		/**
		 * 注册监听器，当前节点不存在，创建该节点：未抛出异常及错误日志
		 *  注册子节点触发type=[CHILD_ADDED]
		 *  更新触发type=[CHILD_UPDATED]
		 *
		 *  zk挂掉type=CONNECTION_SUSPENDED,，一段时间后type=CONNECTION_LOST
		 *  重启zk：type=CONNECTION_RECONNECTED, data=null
		 *  更新子节点：type=CHILD_UPDATED, data=ChildData{path='/zktest111/tt1', stat=4294979983,4294979993,1501037475236,1501037733805,2,0,0,0,6,0,4294979983
		 , data=[55, 55, 55, 55, 55, 55]}
		 ​
		 *  删除子节点type=CHILD_REMOVED
		 *  更新根节点：不触发
		 *  删除根节点：不触发  无异常
		 *  创建根节点：不触发
		 *  再创建及更新子节点不触发
		 *
		 * 重启时，与zk连接失败
		 */
		System.out.println("创建监听器");
		PathChildrenCache watcher = new PathChildrenCache(zkClient, PATH, true/*,false, service*/);
		watcher.getListenable().addListener(new PathChildrenCacheListener() {
			public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
				//节点改变事件  这里直接打印
				printChildrenCacheEvent(pathChildrenCacheEvent);
			}
		});
        /*PathChildrenCache.StartMode说明如下
        *POST_INITIALIZED_EVENT
        *1、在监听器启动的时候即，会枚举当前路径所有子节点，触发CHILD_ADDED类型的事件
        * 2、同时会监听一个INITIALIZED类型事件
        * NORMAL异步初始化cache
        * POST_INITIALIZED_EVENT异步初始化，初始化完成触发事件PathChildrenCacheEvent.Type.INITIALIZED
        /*NORMAL只和POST_INITIALIZED_EVENT的1情况一样，不会ALIZED类型事件触发

        /*BUILD_INITIAL_CACHE 不会触发上面两者事件,同步初始化客户端的cache，及创建cache后，就从服务器端拉入对应的数据         */
		System.out.println("缓存节点数据");
		watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
		System.out.println("注册watcher成功...");
	}
	public static void printChildrenCacheEvent(PathChildrenCacheEvent event){
		ChildData data=event.getData();
		System.out.println("类型："+event.getType()+" 路径："+data.getPath()+" 数据："+new String(data.getData())+"状态："+data.getStat().toString());
	}
	public static void registerNodeCache(CuratorFramework client) throws Exception{
		/*
		 * 节点路径不存在时，set不触发监听
		 * 节点路径不存在，，，创建事件触发监听
		 * 节点路径存在，set触发监听
		 * 节点路径存在，delete触发监听
		 *
		 *
		 * 节点挂掉，未触发任何监听
		 * 节点重连，未触发任何监听
		 * 节点重连 ，恢复监听
		 * */
		final NodeCache nodeCache = new NodeCache(client, PATH, false);
		nodeCache.getListenable().addListener(new NodeCacheListener() {
			public void nodeChanged() throws Exception {
				System.out.println("当前节点："+nodeCache.getCurrentData());
			}
		});
		//如果为true则首次不会缓存节点内容到cache中，默认为false,设置为true首次不会触发监听事件
		nodeCache.start(true);
	}

	public static void registTreeCache(CuratorFramework client) throws Exception {
		/**
		 * TreeCache.nodeState == LIVE的时候，才能执行getCurrentChildren非空,默认为PENDING
		 * 初始化完成之后，监听节点操作时 TreeCache.nodeState == LIVE
		 *
		 * maxDepth值设置说明，比如当前监听节点/t1，目录最深为/t1/t2/t3/t4,则maxDepth=3,说明下面3级子目录全
		 * 监听，即监听到t4，如果为2，则监听到t3,对t3的子节点操作不再触发
		 * maxDepth最大值2147483647
		 *
		 * 初次开启监听器会把当前节点及所有子目录节点，触发[type=NODE_ADDED]事件添加所有节点（小等于maxDepth目录）
		 * 默认监听深度至最低层
		 * 初始化以[type=INITIALIZED]结束
		 *
		 *  [type=NODE_UPDATED],set更新节点值操作，范围[当前节点，maxDepth目录节点](闭区间)
		 *
		 *
		 *  [type=NODE_ADDED] 增加节点 范围[当前节点，maxDepth目录节点](左闭右闭区间)
		 *
		 *  [type=NODE_REMOVED] 删除节点， 范围[当前节点， maxDepth目录节点](闭区间),删除当前节点无异常
		 *
		 *  事件信息
		 *  TreeCacheEvent{type=NODE_ADDED, data=ChildData{path='/zktest1',                     stat=4294979373,4294979373,1499850881635,1499850881635,0,0,0,0,2,0,4294979373
		 , data=[116, 49]}}
		 *
		 */
		final TreeCache treeCache = TreeCache.newBuilder(client, PATH).setCacheData(true).setMaxDepth(2).build();
		treeCache.getListenable().addListener(new TreeCacheListener() {
			public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
				System.out.println("treeCacheEvent: "+treeCacheEvent);
			}
		});
		//没有开启模式作为入参的方法
		treeCache.start();
	}
}
