package com.socket.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo_TCPServer {
    public static void main(String[] args) {
//        Demo1();
        System.out.println("服务端启动！！！");

        try {
            ServerSocket server = new ServerSocket(12345);

            while(true) {
                final Socket socket = server.accept();						//接受客户端的请求
                new Thread() {
                    public void run() {
                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));		//将字节流包装成了字符流
                            PrintStream ps = new PrintStream(socket.getOutputStream());					//PrintStream中有写出换行的方法

                            //ps.println("欢迎咨询黑马程序员");
                            boolean flag = true;

                            while(flag){
                                String mes = br.readLine();
                                if (mes.isEmpty())
                                {
                                    flag = false;
                                    break;
                                }
                                System.out.println(mes);
                                ps.println("server reply message : " + mes);
                            }
                            System.out.println("server closed!!!");
                           // System.out.println(br.readLine());
                            socket.close();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                }.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Demo1() {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);

            Socket socket = serverSocket.accept();//接受客户端的请求

            InputStream is = socket.getInputStream();//获取客户端输入流
            OutputStream os = socket.getOutputStream();//获取客户端的输出流
            os.write("发送到客户端数据".getBytes());//服务器向客户端写出数据


            byte[] arr = new byte[1024];
            int len = is.read(arr);								//读取服务器发过来的数据
            System.out.println(new String(arr,0,len));       	//将数据转换成字符串并打印
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
