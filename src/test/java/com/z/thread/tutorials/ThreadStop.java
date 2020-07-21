package com.z.thread.tutorials;

public class ThreadStop implements Runnable {

    private static boolean run=true;

    public synchronized void stop(){
        this.run=false;
    }

    public synchronized void keepRun(){
        this.run=true;
    }

    @Override
    public void run() {
        while (run){
            System.out.println("running...");
        }
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
