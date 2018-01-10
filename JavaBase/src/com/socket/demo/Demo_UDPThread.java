package com.socket.demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.Scanner;

public class Demo_UDPThread {
    public static void main(String[] args) {

        new Receive().start();

        new Send().start();
    }
}

class Send extends Thread{
    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket();//创建socket相当于创建码头
            Scanner sc =  new Scanner(System.in);

            while(true){
                String str = sc.nextLine();
                if ("quit".equals(str))
                    break;

                //创建packet相当于创建集装箱
                DatagramPacket packet = new DatagramPacket(str.getBytes(),str.getBytes().length, InetAddress.getByName("127.0.0.1"),6666);
                socket.send(packet);//发货
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class  Receive extends Thread{
    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(6666);//创建packet相当于创建集装箱
            DatagramPacket packet = new DatagramPacket(new byte[1024],1024);//创建packet相当于创建集装箱

            while (true)
            {
                socket.receive(packet);//接收货物
                byte[] data = packet.getData();
                int len = data.length;
                String ip = packet.getAddress().getHostAddress();
                int port = packet.getPort();
                System.out.println(ip +":"+port +" " + new String(data,0,len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}