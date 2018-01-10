package com.socket.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Demo_TestTCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",6666);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            boolean flog = true;
            String str = null;
            while(flog){
                str = sc.nextLine();
                System.out.println("client say :" + str);
                dataOutputStream.writeUTF(str);
                dataOutputStream.flush();
                str = dataInputStream.readUTF();
                if (str.equals("bye")){
                    flog = false;
                    System.out.println("server say bye!");
                }else{
                    System.out.println("server say : " + str);
                }
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
