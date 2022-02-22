package com.ourgut.likou.other;

/**
 * @author ganxinming
 * @createDate 2021/9/16
 * @description
 * 面试题 16.25. LRU 缓存
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 *
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2 /* 缓存容量 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRUCache cache = new LRUCache （2）
 * 	cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * 思路：1.定义map和cap容量
 */
public class LRUCache {
		Map<Integer,Integer> map;
		int capacity;
    public LRUCache(int capacity) {
			map=new LinkedHashMap<>(capacity);
			this.capacity=capacity;
		}

		public int get(int key) {
			if (!map.containsKey(key)){
				return -1;
			}
			int value=map.get(key);
			//每次都更新
			map.remove(key);
			map.put(key,value);
			return value;
		}

		public void put(int key, int value) {
			if (map.containsKey(key)){
				//更新
				map.remove(key);
				map.put(key,value);
			}else{
				//移除第一个key，因为他最老
				if (map.size() == capacity){
					map.remove(map.keySet().iterator().next());
				}
				map.put(key,value);
			}
		}

		class MyLinkedMap{
			int capacity;
			MyNode head,tail;
			Map<Integer,Integer> map=new HashMap<>();
			MyLinkedMap(int capacity){
				this.capacity=capacity;
			}
			public void put(int key,int value){
				map.put(key,value);
				MyNode node=new MyNode(key,value);
				head.pre=node;
				node.next=head;
				head=node;
			}
			public int get(int key){
				if (!map.containsKey(key)){
					return -1;
				}
				int value=map.get(key);
				MyNode tmp=head;
				while (tmp != null){
					if (tmp.key == key){
						MyNode n=tmp.pre;
						n.next=tmp.next;
						tmp.next.pre=n;

						tmp.next=head;
						head.pre=tmp;
						head=tmp;
						return value;
					}
				}
				return value;
			}
		}
		class MyNode{
			int key,value;
			MyNode next,pre;
			MyNode(int key,int value){
				this.key=key;
				this.value=value;
			}
		}
}
