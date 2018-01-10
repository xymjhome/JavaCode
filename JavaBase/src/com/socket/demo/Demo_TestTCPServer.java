package com.socket.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Demo_TestTCPServer {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            boolean flog = true;
            Socket socket = ss.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);

            String str = null;
            while(flog){
                str = dataInputStream.readUTF();
                if ("bye".equals(str)){
                    flog = false;
                    System.out.println("server say bye!");
                }else{
                    System.out.println("client say :" + str);
                }
                str = sc.nextLine();
                dataOutputStream.writeUTF(str);
                dataOutputStream.flush();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
