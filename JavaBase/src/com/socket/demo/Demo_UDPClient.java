package com.socket.demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/* 1.发送Send
        * 创建DatagramSocket, 随机端口号
        * 创建DatagramPacket, 指定数据, 长度, 地址, 端口
        * 使用DatagramSocket发送DatagramPacket
        * 关闭DatagramSocket
*/
public class Demo_UDPClient {

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            Scanner sc = new Scanner(System.in);

            while(true){
                String str = sc.nextLine();
                if ("quit".equals(str)) {
                    break;
                }
                DatagramPacket dp = new DatagramPacket(str.getBytes(),str.getBytes().length, InetAddress.getByName("127.0.0.1"),12345);
                ds.send(dp);
                System.out.println("Client发送成功！");
            }

            ds.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
