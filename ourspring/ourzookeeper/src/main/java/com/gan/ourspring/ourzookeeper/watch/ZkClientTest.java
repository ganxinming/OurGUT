package com.gan.ourspring.ourzookeeper.watch;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * @author ganxinming
 * @createDate 2021/3/22
 * @description
 */
public class ZkClientTest {

	public static void main(String[] args) {
		ZkClient zkClient = new ZkClient("115.159.202.204:2181");
		System.out.println("Zookeeper session established.");

		zkClient.createPersistent("/xw_client/xw-test", true);
		System.out.println("create node successed.");


	}

	/**
	 * zkclient提供了递归创建节点的接口
	 */
	public static void createPersistent(ZkClient zkClient ){
		zkClient.createPersistent("/xw_client/xw-test", true);
		System.out.println("create node successed.");
	}



	/**
	 * 对子节点变更(节点的变更，不是监听内容的变更)进行监听
	 * @param zkClient
	 * @throws InterruptedException
	 */
	public static void subscribeChildChanges(ZkClient zkClient ) throws InterruptedException {

		zkClient.subscribeChildChanges("/zk_client", new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> children) throws Exception {
				System.out.println(parentPath + "'s child changed," + "currentChildren: " + children);
			}
		});

		zkClient.createPersistent("/zk_client");
		Thread.sleep(1000);
		zkClient.createPersistent("/zk_client/c1");
		Thread.sleep(1000);
		zkClient.delete("/zk_client/c1");
		Thread.sleep(1000);
		zkClient.delete("/zk_client");
		Thread.sleep(1000);
	}

	/**
	 * 对节点内容变更进行监听
	 * @param zkClient
	 */
	public static void subscribeDataChanges(ZkClient zkClient ) throws InterruptedException {

		String path="/zkclient-temp";

		zkClient.subscribeDataChanges("/zkclient-temp",new IZkDataListener(){

			@Override
			public void handleDataChange(String s, Object o) throws Exception {
				System.out.println("该节点内容被更新,更改为: " + o);
			}

			@Override
			public void handleDataDeleted(String s) throws Exception {
				System.out.println("该节点被删除");
			}
		});

		//读取节点的值
		Object o = zkClient.readData("/zkclient-temp");
		System.out.println("zkclient-temp节点内容为: " + o);

		//内容被更新
		zkClient.writeData(path, "119");
		Thread.sleep(1000);

		zkClient.delete(path);
		Thread.sleep(1000);
	}

	/**
	 * 删除节点
	 * @param zkClient
	 */
	public static void deleteNode(ZkClient zkClient ){
		boolean delete = zkClient.deleteRecursive("/xw_client");
		if (delete) {
			System.out.println("delete node successed.");
		}
	}
}
