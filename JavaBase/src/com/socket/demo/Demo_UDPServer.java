package com.socket.demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/* 2.接收Receive
         * 创建DatagramSocket, 指定端口号
         * 创建DatagramPacket, 指定数组, 长度
         * 使用DatagramSocket接收DatagramPacket
         * 关闭DatagramSocket
         * 从DatagramPacket中获取数据
         * 3.接收方获取ip和端口号
         * String ip = packet.getAddress().getHostAddress();
         * int port = packet.getPort();
*/
public class Demo_UDPServer {

     public static void main(String[] args) {
         try {
             DatagramSocket socket = new DatagramSocket(12345);
             DatagramPacket packet = new DatagramPacket(new byte[1024],1024);

             while (true) {
                 socket.receive(packet);
                 byte[] arr = packet.getData();//packet.getData()返回是字节数组，需要用String的构造方法把字节数组转换为String
                 int len = arr.length;
                 String data = new String(arr,0,len);
                 System.out.println(data);
                 String ip = packet.getAddress().getHostAddress();
                 int port = packet.getPort();
//                 System.out.println(ip + ":"+ port +"  "+ packet.getData().toString());
                 System.out.println(ip + ":"+ port +"  "+ data.toString());
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
