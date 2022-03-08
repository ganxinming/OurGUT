package com.gan.java8.basis.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class QQClient {
    public static void main(String[] args) throws IOException {
        String ip="127.0.0.1";
        //通过这个可以直接连接


        while (true) {
            Socket client = new Socket(ip, Server.PORT);
            //获得输出流，用于发送消息给服务端.
            PrintWriter writer = new PrintWriter(client.getOutputStream());
            System.out.println("请回复:");
            Scanner in = new Scanner(System.in);
            String message = in.nextLine();
            writer.write(message);
            writer.flush();
            client.shutdownOutput();
            //用来读取服务端回复的消息.
            BufferedReader readB = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String info = null;
            while ((info = readB.readLine()) != null) {
                System.out.println("我这边是客户端：接收到服务端的信息是 ---" + info);
            }
            client.shutdownInput();
            writer.flush();
            writer.close();
            client.close();
        }
    }
}
