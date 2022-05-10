package com.z.tomcat.one.server;

import java.io.IOException;
import java.io.InputStream;

public class Request {

    private InputStream request;
    private String uri;

    public Request(InputStream in) {
        this.request = in;
    }

    public void parse() {
        byte[] buffer = new byte[2048];
        StringBuffer req = new StringBuffer(2048);

        int i;
        try {
            request.read(buffer);
            for (byte b : buffer) {
                req.append((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(req.toString());
        uri = parseUri(req.toString());

    }

    private String parseUri(String httpString) {

        int index1, index2;
        index1 = httpString.indexOf(' ');
        if (index1 != -1) {
            index2 = httpString.indexOf(' ', index1 + 1);
            if (index1 < index2) {
                return httpString.substring(index1 + 1, index2);
            }
        }

        return null;
    }

    public String getUri() {
        return uri;
    }
}
