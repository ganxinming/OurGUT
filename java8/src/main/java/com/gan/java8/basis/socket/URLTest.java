package com.gan.java8.basis.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {

    public static void main(String[] args) {
        try {
            URL url=new URL("http://www.baidu.com?name=张三");
            System.out.println(url.getHost());
            System.out.println(url.getPath());
            System.out.println(url.getQuery());
            System.out.println(url.getAuthority());
            System.out.println(url.getPort());
            readPage("http://www.bilibili.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //通过URL读取网页内容
    public static void readPage(String path) throws IOException {
        URL url=new URL(path);
        //获得输入流
        InputStream in=url.openStream();
        //转成字符输入流
        InputStreamReader read=new InputStreamReader(in,"UTF-8");
        //套层buffer，加快
        BufferedReader read1=new BufferedReader(read);
        String data=null;
        //bufferReader专有的readLine，一次读一行很好用
        while ((data=read1.readLine()) != null){
            System.out.println(data);
        }
        /**或者用下面的也行
         * char [] cc=new char[64];
         * int index=0;
         * StringBuffer ss=new StringBuffer();
         * while ((index=read1.read(cc)）!= -1){
         *      ss.append(new String(cc,0,index));
         * }
         * System.out.println(ss);
         *
         */
        read1.close();
        read.close();
        in.close();
    }
}
