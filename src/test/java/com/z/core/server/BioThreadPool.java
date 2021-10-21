package com.z.core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioThreadPool {
    //创建线程池
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static class Handler implements Runnable{
        Socket client;

        public Handler(Socket client){
            this.client=client;
        }

        @Override
        public void run() {
            //创建字符缓存输入流
            BufferedReader bufferedReader = null;
            //创建字符写入流
            PrintWriter printWriter = null;

            try {
                bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                printWriter = new PrintWriter(client.getOutputStream(),true);

                String inputLine = null;

                long a = System.currentTimeMillis();
                while ((inputLine= bufferedReader.readLine())!=null){
                    printWriter.println(inputLine);
                }
                long b = System.currentTimeMillis();

                System.out.println("此线程花费了："+(b-a)+"秒！");
            }catch (Exception e){
                try {
                    bufferedReader.close();
                    printWriter.close();
                    client.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8008);
        Socket client = null;
        while (true){
            client = serverSocket.accept();
            System.out.println(client.getRemoteSocketAddress()+"地址的客户端连接成功！");
            executorService.submit(new Handler(client));
        }
    }
}
