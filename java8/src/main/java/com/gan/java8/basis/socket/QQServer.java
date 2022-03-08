package com.gan.java8.basis.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class QQServer {
     public static final int PORT=8888;
    public static void main(String[] args) throws IOException {
        //创建ServerSocket
        ServerSocket server=new ServerSocket(PORT);
        //如果未得到客户端请求连接，下面代码是阻塞的。
        //accept()监听是否有客户端连接，如果有，返回这个socket。
        try {

            while (true) {
                Socket socket = server.accept();
                //从Socket中得到输入流(从客户端输入的信息),用Buffer套着，提高效率.
                BufferedReader readB = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String info = null;
                //readLine，Buffer流的独特读法，可以直接读一行。
                while ((info = readB.readLine()) != null) {
                    System.out.println("我这边是服务端：接收到客户端信息是 ---" + info);
                }
                //关闭输入流
                socket.shutdownInput();
                //得到客户端消息，下面就是回客户端，服务端将要发送的消息
                OutputStream out = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(out);
                System.out.println("请回复:");
                Scanner in = new Scanner(System.in);
                String message = in.nextLine();
                writer.write(message);
                //因为套用了buffer，还有在缓冲区数据需要刷新一下。
                writer.flush();
                //关闭连接
                writer.close();
                readB.close();
                socket.close();

            }
        }
        finally {
            server.close();
        }

    }
}
