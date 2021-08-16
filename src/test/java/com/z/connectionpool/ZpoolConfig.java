package com.z.connectionpool;

public class ZpoolConfig {

    private String url;
    private String username;
    private String password;

    private int ConnSize = 10;

    public ZpoolConfig(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public ZpoolConfig(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnSize() {
        return ConnSize;
    }

    public void setConnSize(int connSize) {
        ConnSize = connSize;
    }
}
