package com.ourspring.io.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import com.ourspring.io.entity.People;

/**
 * @author ganxinming
 * @createDate 2021/8/6
 * @description
 * java.io.ObjectOutputStream代表对象输出流，它的writeObject(Object obj)方法可对参数指定的obj对象进行序列化，把得到的字节序列写到一个目标输出流中。
 * 　　java.io.ObjectInputStream代表对象输入流，它的readObject()方法从一个源输入流中读取字节序列，再把它们反序列化为一个对象，并将其返回。
 * 　　只有实现了Serializable和Externalizable接口的类的对象才能被序列化。Externalizable接口继承自 Serializable接口，实现Externalizable接口的类
serialVersionUID作用：
 如果没有serialVersionUID
	A序列化，存下来，反序列化是没问题的。但是如果此时A类扩展了新字段，那么再反序列化则报错。因为没有指定ID，默认采用java的摘要算法，生成个新的serialVersionUID
	如果类发生变化，都会导致新生成serialVersionUID，这样就无法反序列化老的数据，所以在类里需要手动指定serialVersionUID
 如果人为指定了serialVersionUID
 	A序列化，存下来，无论是否扩展新字段，都可以反序列化，因为人为指定了serialVersionUID，如果改动了ID则也无法反序列化，
 	所以无论是新增还是删减，serialVersionUID生成后就不要动了。
 */
public class TestSerializable {

	public String path=this.getClass().getResource("/").getPath();

	public static void main(String[] args) throws Exception {
		TestSerializable testSerializable=new TestSerializable();
//		testSerializable.serializable();
		testSerializable.deserializePerson();
//		testSerializable.serializableOther();
	}

	//序列化对象
	private void serializable() throws Exception {
		People people=new People("gan",18,12);
		ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(new File(path+"a.txt")));
		objectOutputStream.writeObject(people);
		System.out.println("序列化成功");
		objectOutputStream.close();
	}

	//反序列化
	private void deserializePerson() throws Exception {
		ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(new File(path+"a.txt")));
		People object = (People)inputStream.readObject();
		System.out.println("序列化后："+object.toString());
	}


	/**
	 * 序列化其他值
	 * @throws Exception
	 * 对于写入的顺序，和读出的顺序需要一样。
	 * 如果写入int，utf，那么读取时，就不能先读utf，后读int，否则报错
	 */
	private void serializableOther() throws Exception {

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File(path+"b.txt")))) {
			// 写入int:
			output.writeInt(12345);
			// 写入String:
			output.writeUTF("Hello");
			// 写入Object:
			output.writeObject(Double.valueOf(123.456));
			output.writeInt(789);
		}

		System.out.println(Arrays.toString(buffer.toByteArray()));

		ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(new File(path+"b.txt")));
		System.out.println(inputStream.readInt());
		System.out.println(inputStream.readUTF());
		System.out.println(inputStream.readObject());
		System.out.println(inputStream.readInt());

	}

}
