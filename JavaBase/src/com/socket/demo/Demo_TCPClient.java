package com.socket.demo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * * 1.客户端
 * 创建Socket连接服务端(指定ip地址,端口号)通过ip地址找对应的服务器
 * 调用Socket的getInputStream()和getOutputStream()方法获取和服务端相连的IO流
 * 输入流可以读取服务端输出流写出的数据
 * 输出流可以写出数据到服务端的输入流
 */
public class Demo_TCPClient {
    public static void main(String[] args) {
//        demo1();

        System.out.println("客户端启动！！！");
        Scanner sc = new Scanner(System.in);
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));		//将字节流包装成了字符流
            PrintStream ps = new PrintStream(socket.getOutputStream());					//PrintStream中有写出换行的方法

            while (true) {
                String mes = sc.nextLine();
                if ("quit".equals(mes))
                    break;
                //ps.println("我想报名黑马程序员");
                ps.println(mes);
                System.out.println(br.readLine());
                //System.out.println(br.readLine());
                //ps.println("大哭!!!能不能给次机会");
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void demo1() {
        try {
            Socket socket = new Socket("127.0.0.1",6666);

            InputStream is = socket.getInputStream();//获取客户端输入流
            OutputStream os = socket.getOutputStream();//获取客户端的输出流
            byte[] arr = new byte[1024];
            int len = is.read(arr);								//读取服务器发过来的数据
            System.out.println(new String(arr,0,len));       	//将数据转换成字符串并打印
            os.write("发送到服务端数据".getBytes());//客户端向服务器写数据
           socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
