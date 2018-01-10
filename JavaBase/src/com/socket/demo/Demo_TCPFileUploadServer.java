package com.socket.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo_TCPFileUploadServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            while(true){
                Socket socket = serverSocket.accept();

                new Thread(){
                    @Override
                    public void run() {
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintStream printStream = new PrintStream(socket.getOutputStream());

                            String fileName = bufferedReader.readLine();
                            File dirFile = new File("upload");
                            dirFile.mkdir();
                            File file = new File(dirFile,fileName);
                            if(file.exists()){
                                System.out.println("server : file is exists");
                                printStream.println("yes");
                            }else{
                                System.out.println("server : file is not exists");
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                byte[] arr = new byte[8192];
                                int len = 0;
                                while((len = bufferedReader.read()) != -1){
                                    fileOutputStream.write(arr,0,len);
                                }
                                fileOutputStream.close();
                                socket.close();
                            }

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
}
