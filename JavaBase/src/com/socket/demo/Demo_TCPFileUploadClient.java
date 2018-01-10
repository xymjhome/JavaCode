package com.socket.demo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Demo_TCPFileUploadClient {
    public static void main(String[] args) {
        File file = getFile();
        try {
            Socket socket = new Socket("127.0.0.1",6666);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            printStream.println(file.getName());


            String res = bufferedReader.readLine();
            System.out.println("receive data from server is :" + res);
            if("yes".equals(res))
            {
                System.out.println("您上传的文件已经存在,请不要重复上传");
                System.out.println("client sout file is exist");
                socket.close();
                return;
            }
            else{
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] arr = new byte[8192];
                int len = 0;
                while ((len = fileInputStream.read(arr)) != -1)
                {
                    printStream.write(arr,0,len);
                }
                fileInputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getFile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个文件路径：");

        while(true){
            File file = new File(sc.nextLine());
            if (!file.exists()){
                System.out.println("文件不存在，请重新输入文件路径：");
            }else if(!file.isFile()){
                System.out.println("请输入文件路径：");
            }else
            {
                return file;
            }

        }
    }
}
