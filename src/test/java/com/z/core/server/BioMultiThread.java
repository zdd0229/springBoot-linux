package com.z.core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BioMultiThread {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8008);

        while (true){
            Socket client = serverSocket.accept();

            new Thread(() -> {
                System.out.println(client.getRemoteSocketAddress()+"地址的客户端连接成功！");
                //创建字符缓存输入流
                BufferedReader bufferedReader = null;
                //创建字符写入流
                PrintWriter printWriter = null;

                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    printWriter = new PrintWriter(client.getOutputStream(),true);

                    String inputLine = null;
                    while ((inputLine= bufferedReader.readLine())!=null){
                        printWriter.println(inputLine);
                    }
                }catch (Exception e){
                    try {
                        bufferedReader.close();
                        printWriter.close();
                        client.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }).start();

        }
    }
}
