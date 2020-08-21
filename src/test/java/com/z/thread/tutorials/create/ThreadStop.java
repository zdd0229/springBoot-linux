package com.z.thread.tutorials.create;

public class ThreadStop implements Runnable {

    private static boolean run=true;

    public synchronized void stop(){
        this.run=false;
    }

    public synchronized void keepRun(){
        this.run=true;
    }

    private long num=0;

    @Override
    public void run() {
        while (run){
            num++;
            System.out.println("running...");
        }
        System.out.println(num);
    }

    public static void main(String[] args) {

        ThreadStop stop =new ThreadStop();
        Thread thread =new Thread(stop);

        thread.start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop.stop();
    }
}
