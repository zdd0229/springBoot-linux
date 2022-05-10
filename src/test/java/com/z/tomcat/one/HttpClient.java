package com.z.tomcat.one;

import java.io.*;
import java.net.Socket;

public class HttpClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream os = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(os, true);
        writer.println("GET /index.jsp HTTP/1.1");
        writer.println("Host: localhost:8080");
        writer.println("Connection: Close");
        writer.println();

        boolean loop = true;
        StringBuffer resp = new StringBuffer(8096);
        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (loop) {
            if (reader.ready()) {
                int i = -1;
                while ((i = reader.read()) != -1) {
                    resp.append((char) i);
                }
            loop = false;
            }
            Thread.currentThread().sleep(50);
        }

        System.out.println(resp.toString());

        socket.close();
    }

}
