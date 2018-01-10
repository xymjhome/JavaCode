package com.socket.demo;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo_UDPGUI {
    public static void main(String[] args) {
        new GUIChat();
    }
}

class GUIChat extends Frame{

    private TextField tf;
    private Button send;
    private Button log;
    private Button clear;
    private Button shake;
    private TextArea viewText;
    private TextArea sendText;
    private DatagramSocket socket;
    private BufferedWriter bw;

    public GUIChat() {
        init();
        southPanel();
        centerPanel();
        event();
    }
    public void init() {
        this.setLocation(500,50);
        this.setSize(600,600);
        new Receive().start();
        try {
            socket = new DatagramSocket();
            bw = new BufferedWriter(new FileWriter("config.txt",true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    private void southPanel() {
        Panel panel = new Panel();
        tf = new TextField(16);
        tf.setText("127.0.0.1");
        send = new Button("Send");
        log = new Button("Log");
        clear = new Button("Clear");
        shake = new Button("Shake");
        panel.add(tf);
        panel.add(send);
        panel.add(log);
        panel.add(clear);
        panel.add(shake);
        this.add(panel,BorderLayout.SOUTH);
    }

    public void centerPanel() {
        Panel center = new Panel();
        viewText = new TextArea();
        sendText = new TextArea(5,1);
        viewText.setBackground(Color.white);
        viewText.setEditable(false);
        sendText.setBackground(Color.white);
        sendText.setFont(new Font("xxx", Font.PLAIN, 20));
        viewText.setFont(new Font("xxx", Font.PLAIN, 20));
        center.setLayout(new BorderLayout());
        center.add(sendText,BorderLayout.SOUTH);
        center.add(viewText,BorderLayout.CENTER);
        this.add(center,BorderLayout.CENTER);
    }

    public void event() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    socket.close();
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send();
            }
        });

        sendText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    send();
                }
            }
        });

        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logFile();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewText.setText("");
            }
        });

        shake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               // shake();
                try {
                    send(new byte[]{-1},tf.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void shake() {
        int x = this.getLocation().x;
        int y = this.getLocation().y;

        int count = 30;
        while(count-- > 0){
            try {
                this.setLocation(x +10,y+10);
                Thread.sleep(10);
                this.setLocation(x - 10,y+10);
                Thread.sleep(10);
                this.setLocation(x +10,y-10);
                Thread.sleep(10);
                this.setLocation(x - 10,y-10);
                Thread.sleep(10);
                this.setLocation(x ,y);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void logFile() {
        try {
            bw.flush();//刷新缓冲区
            FileInputStream fis = new FileInputStream("config.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//在内存中创建缓冲区

            int len;
            byte[] arr = new byte[8192];
            while((len = fis.read(arr)) != -1)
            {
                baos.write(arr,0,len);
            }

            String logStr = baos.toString();//将内存中的内容转换成了字符串
            viewText.setText(logStr);

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(byte[] bytes,String ip) throws IOException {
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length, InetAddress.getByName(ip),12345);
        socket.send(packet);
    }
    public void send() {
        String str = sendText.getText();
        try {
            String ip = tf.getText();
            ip = ip.trim().length() == 0 ? "255.255.255.255" : ip;
            send(str.getBytes(),ip);
            String time = getTime();
            String message = time + " 我对 "+ ("255.255.255.255".equals(ip) ? "所有人" : ip) + " 说:\r\n" + str +"\r\n\r\n";
            viewText.append(message);
            bw.write(message);
            sendText.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getTime() {
        Date dt = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        return format.format(dt);
    }

    class Receive extends Thread{
        @Override
        public void run() {
            try {
                DatagramSocket socket = new DatagramSocket(12345);
                DatagramPacket packet = new DatagramPacket(new byte[8192],8192);

                while (true) {
                    socket.receive(packet);//接收信息
                    byte[] mes = packet.getData();//获取字节数据
                    int len = packet.getLength();//获取有效的字节数据
                    if (mes[0] == -1 && len == 1)//如果发过来的数组第一个存储的值是-1,并且数组长度是1
                    {
                        shake();//调用震动方法
                        continue;//终止本次循环,继续下次循环,因为震动后不需要执行下面的代码
                    }
                    String message = new String(mes,0,len);
                    message =new StringBuffer(message).reverse().toString();//转换成字符串

                    String ip =packet.getAddress().getHostAddress();

                    String time = getTime();
                    String str =time +" " + ip + " 对我说：\r\n"+ message +"\r\n\r\n";
                    viewText.append(str);
                    bw.write(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
