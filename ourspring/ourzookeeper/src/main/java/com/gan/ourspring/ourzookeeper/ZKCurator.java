package com.gan.ourspring.ourzookeeper;

import java.util.ArrayList;
import java.util.List;

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
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.CloseableUtils;
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
 *
 * Curator解决了很多zookeeper客户端非常底层的细节开发工作，包括连接重连、反复注册wathcer(原生的watch只能用一次)和NodeExistsException 异常等。
 *
 * 事件监听
 * zookeeper原生支持通过注册watcher来进行事件监听，但是其使用不是特别方便，需要开发人员自己反复注册watcher，比较繁琐。
 *
 * Curator引入Cache来实现对zookeeper服务端事务的监听。Cache是Curator中对事件监听的包装，其对事件的监听其实可以近似看作是一个本地缓存视图和远程Zookeeper视图的对比过程。
 * 同时，Curator能够自动为开发人员处理反复注册监听，从而大大简化原生api开发的繁琐过程。
 *
 *主要功能:
 * 监听：
 * 1.NodeCache可以监听指定的节点，注册监听器后，节点的变化会通知相应的监听器.
 * 2.Path Cache  用来监听ZNode的子节点事件，包括added、updateed、removed，Path Cache会同步子节点的状态，产生的事件会传递给注册的PathChildrenCacheListener。
 * 3.Path Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。
 * 选举：
 * curator提供了两种方式，分别是
 * 1.Leader Latch：随机从候选着中选出一台作为leader
 * 2.Leader Election:通过LeaderSelectorListener可以对领导权进行控制， 在适当的时候释放领导权，这样每个节点都有可能获得领导权
 * 分布式锁：
 * 1.可重入锁Shared Reentrant Lock：Shared意味着锁是全局可见的， 客户端都可以请求锁
 * 2.不可重入锁Shared Lock
 * 3.可重入读写锁Shared Reentrant Read Write Lock
 * 4.信号量Shared Semaphore
 *
 */
public class ZKCurator {


	private final static String ZK_URL = "115.159.202.204:2181";

	private final static int TIME_OUT = 5000;

	private static ZooKeeper zkClient = null;
	//三个host，分别代表三个节点
	private static final String ADDR = "115.159.202.204:2181";
	private static final String PATH = "/zk_test";

	public static void main(String[] args) throws InterruptedException {

		//创建ZKCurator类，通过CuratorFrameworkFactory创建
		final CuratorFramework zkClient = getClient();
		zkClient.start();
		System.out.println(zkClient.checkExists());
		System.out.println("开始创建Zookeeper客户端...");
		try {
			registerPathCacheWatcher(zkClient);
			registerNodeCache(zkClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("监听器创建完毕...");
		//直接堵塞当前线程   因为zookeeper那边有独立的线程管理，这边堵塞避免因为前台进程没有了进程被结束
		Thread.sleep(Integer.MAX_VALUE);
		zkClient.close();
	}

	/**
	 * Node Cache
	 * 节点监听器
	 * @param zkClient
	 * @throws Exception
	 * zookeeper原生支持通过注册watcher来进行事件监听，但是其使用不是特别方便，需要开发人员自己反复注册watcher，比较繁琐。
	 *
	 * Curator引入Cache来实现对zookeeper服务端事务的监听。Cache是Curator中对事件监听的包装，其对事件的监听其实可以近似看作是一个本地缓存视图和远程Zookeeper视图的对比过程。
	 * 同时，Curator能够自动为开发人员处理反复注册监听，从而大大简化原生api开发的繁琐过程。
	 */
	private static void registerNodeCacheWatcher(CuratorFramework zkClient) throws Exception {
		final NodeCache nodeCache = new NodeCache(zkClient, "/abc");
		nodeCache.start(true);
		nodeCache.getListenable().addListener(()->{
			System.out.println("监听节点发生变化："+nodeCache.getCurrentData().getPath()+"值为："+nodeCache.getCurrentData().getData());
		});

		setData(zkClient, "/abc", "cache1".getBytes());
		setData(zkClient, "/abc", "cache2".getBytes());
		Thread.sleep(1000);

//		nodeCache.close();
	}


	/**
	 * 路径监听器
	 * @param zkClient
	 * @throws Exception
	 *
	 * Path Cache  用来监听ZNode的子节点事件
	 * 用来监听ZNode的子节点事件，包括added、updateed、removed，Path Cache会同步子节点的状态，
	 * 产生的事件会传递给注册的PathChildrenCacheListener
	 */
	private static void registerPathCacheWatcher(CuratorFramework zkClient) throws Exception {
		/**
		 *  注册子节点触发type=[CHILD_ADDED]
		 *  更新触发type=[CHILD_UPDATED]
		 *
		 *  zk挂掉type=CONNECTION_SUSPENDED,，一段时间后type=CONNECTION_LOST
		 *  重启zk：type=CONNECTION_RECONNECTED, data=null
		 *  更新子节点：type=CHILD_UPDATED, data=ChildData{path='/zktest111/tt1', stat=4294979983,4294979993,1501037475236,1501037733805,2,0,0,0,6,0,4294979983
		 , data=[55, 55, 55, 55, 55, 55]}:触发
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

		watcher.getListenable().addListener((client1, event) -> {
			switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED:" + event.getData().getPath());
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED:" + event.getData().getPath());
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED:" + event.getData().getPath());
					break;
				case CONNECTION_LOST:
					System.out.println("CONNECTION_LOST:" + event.getData().getPath());
					break;
				case CONNECTION_RECONNECTED:
					System.out.println("CONNECTION_RECONNECTED:" + event.getData().getPath());
					break;
				case CONNECTION_SUSPENDED:
					System.out.println("CONNECTION_SUSPENDED:" + event.getData().getPath());
					break;
				case INITIALIZED:
					System.out.println("INITIALIZED:" + event.getData().getPath());
					break;
				default:
					break;
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

	/**
	 * Path Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。
	 *
	 * @param client
	 * @throws Exception
	 */
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

		treeCache.getListenable().addListener((client1, event) -> {
			switch (event.getType()){
				case NODE_ADDED:
					System.out.println("NODE_ADDED:" + event.getData().getPath());
					break;
				case NODE_REMOVED:
					System.out.println("NODE_REMOVED:" + event.getData().getPath());
					break;
				case NODE_UPDATED:
					System.out.println("NODE_UPDATED:" + event.getData().getPath());
					break;
				case CONNECTION_LOST:
					System.out.println("CONNECTION_LOST:" + event.getData().getPath());
					break;
				case CONNECTION_RECONNECTED:
					System.out.println("CONNECTION_RECONNECTED:" + event.getData().getPath());
					break;
				case CONNECTION_SUSPENDED:
					System.out.println("CONNECTION_SUSPENDED:" + event.getData().getPath());
					break;
				case INITIALIZED:
					System.out.println("INITIALIZED:" + event.getData().getPath());
					break;
				default:
					break;
			}
		});
		String path="/cc";
		client.create().withMode(CreateMode.PERSISTENT).forPath(path);
		Thread.sleep(1000);

		client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
		Thread.sleep(1000);

		setData(client, path, "test".getBytes());
		Thread.sleep(1000);

		client.delete().forPath(path + "/c1");
		Thread.sleep(1000);

		client.delete().forPath(path);
		Thread.sleep(1000);

		//没有开启模式作为入参的方法
		treeCache.start();
	}


	/**
	 * 创建节点，可以根据情况调整节点
	 * @param client
	 * @throws Exception
	 */
	public static void createNode(CuratorFramework client) throws Exception {
		/**
		 * 如果不确定父节点是否存在，可以创建他们creatingParentsIfNeeded
		 * 设置节点权限  withACL
		 *
		 */
		client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath("/name","7".getBytes());
	}


	/**
	 * 创建CuratorFramework
	 * @return
	 *
	 * 为了实现不同的Zookeeper业务之间的隔离，需要为每个业务分配一个独立的命名空间（NameSpace）其实就是创建个根节点
	 */
	public static CuratorFramework getClient() {
		return CuratorFrameworkFactory.builder()
				.connectString(ADDR)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.connectionTimeoutMs(15 * 1000) //连接超时时间，默认15秒
				.sessionTimeoutMs(60 * 1000) //会话超时时间，默认60秒
				.namespace("test") //设置命名空间
				.build();
	}



	public static void create(final CuratorFramework client, final String path, final byte[] payload) throws Exception {
		client.create().creatingParentsIfNeeded().forPath(path, payload);
	}

	public static void createEphemeral(final CuratorFramework client, final String path, final byte[] payload) throws Exception {
		client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
	}

	public static String createEphemeralSequential(final CuratorFramework client, final String path, final byte[] payload) throws Exception {
		return client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
	}

	public static void setData(final CuratorFramework client, final String path, final byte[] payload) throws Exception {
		client.setData().forPath(path, payload);
	}

	public static void delete(final CuratorFramework client, final String path) throws Exception {
		client.delete().deletingChildrenIfNeeded().forPath(path);
	}

	public static void guaranteedDelete(final CuratorFramework client, final String path) throws Exception {
		client.delete().guaranteed().forPath(path);
	}

	public static String getData(final CuratorFramework client, final String path) throws Exception {
		return new String(client.getData().forPath(path));
	}

	public static List<String> getChildren(final CuratorFramework client, final String path) throws Exception {
		return client.getChildren().forPath(path);
	}

	//=====================================================================================  待开发

	/**
	 * 选举：Leader Latch
	 * 随机从候选着中选出一台作为leader，选中之后除非调用close()释放leadship，否则其他的后选择无法成为leader
	 */
	public static void electionLatch(String[] args) {
		List<LeaderLatch> latchList = new ArrayList<>();
		List<CuratorFramework> clients = new ArrayList<>();
		try {
			for (int i = 0; i < 10; i++) {
				CuratorFramework client = getClient();
				client.start();
				clients.add(client);

				final LeaderLatch leaderLatch = new LeaderLatch(client, PATH, "client#" + i);
				leaderLatch.addListener(new LeaderLatchListener() {
					@Override
					public void isLeader() {
						System.out.println(leaderLatch.getId() + ":I am leader. I am doing jobs!");
					}

					@Override
					public void notLeader() {
						System.out.println(leaderLatch.getId() + ":I am not leader. I will do nothing!");
					}
				});
				latchList.add(leaderLatch);
				leaderLatch.start();
			}
			Thread.sleep(1000 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			for (CuratorFramework client : clients) {
				CloseableUtils.closeQuietly(client);
			}

			for (LeaderLatch leaderLatch : latchList) {
				CloseableUtils.closeQuietly(leaderLatch);
			}
		}
	}

	/**
	 * 选举：Leader Election
	 * 通过LeaderSelectorListener可以对领导权进行控制， 在适当的时候释放领导权，这样每个节点都有可能获得领导权。而LeaderLatch则一直持有leadership， 除非调用close方法，否则它不会释放领导权。
	 * @param args
	 */
	public static void leaderElection(String[] args) {
		List<LeaderSelector> selectors = new ArrayList<>();
		List<CuratorFramework> clients = new ArrayList<>();
		try {
			for (int i = 0; i < 10; i++) {
				CuratorFramework client = getClient();
				client.start();
				clients.add(client);

				final String name = "client#" + i;
				LeaderSelector leaderSelector = new LeaderSelector(client, PATH, new LeaderSelectorListenerAdapter() {
					@Override
					public void takeLeadership(CuratorFramework client) throws Exception {
						System.out.println(name + ":I am leader.");
						Thread.sleep(2000);
					}
				});

				leaderSelector.autoRequeue();
				leaderSelector.start();
				selectors.add(leaderSelector);
			}
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			for (CuratorFramework client : clients) {
				CloseableUtils.closeQuietly(client);
			}

			for (LeaderSelector selector : selectors) {
				CloseableUtils.closeQuietly(selector);
			}

		}
	}


	/**
	 * 分布式锁，各种锁（可重入，不可重入）
	 * 分布式计数器、分布式Barrier
	 */



}
