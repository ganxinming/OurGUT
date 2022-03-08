package com.gan.java8.basis.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args)  {

        try {
            InetAddress inetAddress= null;
            //这个不需要加http协议。
            inetAddress = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress.getHostAddress());
            System.out.println(inetAddress.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
