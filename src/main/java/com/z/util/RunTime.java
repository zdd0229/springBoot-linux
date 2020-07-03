package com.z.util;

public class RunTime {

    private Long start;
    private Long end;

    public void start(){
        this.start=System.currentTimeMillis();
    }

    public void end(){
        this.end=System.currentTimeMillis();
    }

    public void print(){
        System.out.println("执行时间："+(end-start));
    }

}
