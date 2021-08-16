package com.z.util;

public class RunTime {

    private Long start;
    private Long end;
    private Long use;

    public void start() {
        start = System.currentTimeMillis();
    }

    public Long end() {
        end = System.currentTimeMillis();
        use = end - start;
        return use;
    }

    public void print() {
        this.end();
        System.out.println("执行时间：" + (end - start) + "ms");
    }

    public long getRuntime() {
        return use;
    }

}
