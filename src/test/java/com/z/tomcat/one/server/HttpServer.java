package com.z.tomcat.one.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    private static boolean shutDown = false;

    protected static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/shutdown";

    private void await() {

        ServerSocket server = null;
        int port = 8080;
        try {
            server = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        //loop waiting for a request
        System.out.println("server start...");
        while (!shutDown){
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;

            try {
                socket = server.accept();
                in = socket.getInputStream();
                out = socket.getOutputStream();

                Request request = new Request(in);
                request.parse();

                Response response = new Response(out);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();

                shutDown = SHUTDOWN_COMMAND.equals(request.getUri());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
