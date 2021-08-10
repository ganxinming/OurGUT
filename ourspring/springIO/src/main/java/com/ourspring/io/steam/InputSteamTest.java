package com.ourspring.io.steam;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ganxinming
 * @createDate 2021/8/7
 * @description
 * Input指从外部读入数据到内存，例如，把文件从磁盘读取到内存，从网络读取数据到内存等等
 * Output指把数据从内存输出到外部，例如，把数据从内存写入到文件，把数据从内存输出到网络等等
 *
 * 字节流和字符流的区别又是什么呢，为什么要出现这两种流？首先明确字节和Byte和字符Character的大小：
 *
 * 1 byte = 8 bit
 * 1 char = 2 byte = 16 bit (Java默认UTF-16编码)
 *
 * 虽然1 bit才是数据真正的最小单位，但1 bit 的信息量太少了。要表示一个有用的信息，需要好几个bit一起表示。
 * 所以除了硬件层面存在1个比特位的寄存器，大多数情况下，字节是数据最小的基本单位。我们熟知的基本型的大小都是8 bit（也就是1字节）的整数倍：
 * short是2byte，int是4byte，long是8byte等
 *
 * 字节流没有缓冲区，是直接输出的，而字符流是输出到缓冲区的。因此在输出时，字节流不调用colse()方法时，
 * 信息已经输出了(但是还是需要关闭流，节约资源)，而字符流只有在调用close()方法关闭缓冲区时或者缓冲区满了，信息才输出。
 * 要想字符流在未关闭时输出信息，则需要手动调用flush()方法，所以这也是为什么最后要flush的原因
 *
 * 2.Java的标准库java.io提供了File对象来操作文件和目录，File对象既可以表示文件，也可以表示目录。
 * 构造一个File对象，即使传入的文件或目录不存在，代码也不会出错，(很恶心，尽量用Path)
 * 因为构造一个File对象，并不会导致任何磁盘操作。只有当我们调用File对象的某些方法的时候，才真正进行磁盘操作
 *
 * 3.Java标准库还提供了一个Path对象，它位于java.nio.file包。Path对象和File对象类似
 *
 */
@Slf4j
public class InputSteamTest {


	public static void main(String[] args) {
		testPath();
	}

	public static void testPath(){
		Path p1 = Paths.get("~","/c.txt" ); // 构造一个Path对象
//		log.info("root:{}",p1.getRoot().toString());
		log.info("parent:{}",p1.getParent());
		log.info("fileName:{}",p1.getFileName().toString());
		Path p2 = p1.toAbsolutePath(); // 转换为绝对路径
		log.info("Absolute:{}",p2.toString());
		Path p3 = p2.normalize(); // 转换为规范路径
		log.info("normalize:{}",p3.toString());
		File f = p3.toFile(); // 转换为File对象
		log.info("file path:{}",f.getPath());
		for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
			System.out.println("  " + p);
		}
	}
}
