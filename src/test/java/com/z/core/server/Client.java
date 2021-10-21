package com.z.core.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket  = new Socket("127.0.0.1",8008);

        for (int i = 0; i < 10; i++) {

            //发消息
            BufferedWriter writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream(),StandardCharsets.UTF_8));
            writer.write("time\n");
            writer.flush();

            //接收返回值
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream(), StandardCharsets.UTF_8));
            String res = reader.readLine();
            System.out.println("res : "+res);
        }

    }
}
