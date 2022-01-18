package com.ourgut.design.pattern.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import lombok.Data;

/**
 * @author ganxinming
 * @createDate 2022/1/17
 * @description
 */
@Data
public class ConcretePrototype implements Cloneable, Serializable {
	private int age=18;

	private List<String> subject= Lists.newArrayList("java","go");
	@Override
	public ConcretePrototype clone() {
		ConcretePrototype cloneType = null;
		try {
			cloneType = (ConcretePrototype) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloneType;
	}

	@Override
	public String toString() {
		return "ConcretePrototype{" +
				"age=" + age +
				", subject=" + subject +
				'}';
	}

	/**
	 * 实现Serializable，深克隆
	 * @return
	 */
	public ConcretePrototype deepClone(){
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);

			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);

			return (ConcretePrototype)ois.readObject();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
