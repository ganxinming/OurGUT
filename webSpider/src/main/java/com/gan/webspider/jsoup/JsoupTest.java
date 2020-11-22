package com.gan.webspider.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * @author ganxinming
 * @createDate 2020/11/22
 * @description 主要还是用来对HTML进行解析。
 */
public class JsoupTest {

	@Test
	public void htmlTest() throws IOException {
		//返回的document对象
		Document doc = Jsoup.connect("https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=2d4ea01bc2ce4677a6fb684adcb961b0/").get();
		//得到的对象所有js方法都可以使用
		Element elementById = doc.getElementById("123");
		Elements elementsByClass = doc.getElementsByClass("p-name");
		for(Element element:elementsByClass){
			Elements em = element.getElementsByTag("em");
			for(Element e:em){
				System.out.println(e.text());
			}
		}
//		String title = doc.title();
//		Element j_ad_12555860 = doc.getElementById("J_AD_12555860");
//		System.out.println(j_ad_12555860.html());
//		String val = j_ad_12555860.val();
//		System.out.println(val);
	}
}
