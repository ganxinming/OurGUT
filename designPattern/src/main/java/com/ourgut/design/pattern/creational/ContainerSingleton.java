package com.ourgut.design.pattern.creational;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ganxinming
 * @createDate 2022/1/11
 * @description
 * 容器单例：适用于管理许多单例的容器
 */
public class ContainerSingleton {

	private ContainerSingleton(){}

	private static Map<String,Object> ioc = new ConcurrentHashMap<String,Object>();

	public static Object getBean(String className){

		synchronized (ioc) {

			if (!ioc.containsKey(className)) {

				Object obj = null;

				try {

					obj = Class.forName(className).newInstance();

					ioc.put(className, obj);

				} catch (Exception e) {

					e.printStackTrace();

				}

				return obj;

			} else {

				return ioc.get(className);

			}

		}

	}
}
