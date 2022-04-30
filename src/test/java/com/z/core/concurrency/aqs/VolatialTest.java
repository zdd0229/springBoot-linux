package com.z.core.concurrency.aqs;

public class VolatialTest {

    public static void main(String[] args) {
        final VT vt = new VT();

        Thread Thread01 = new Thread(vt);
        Thread Thread02 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignore) {
            }
            vt.sign = true;
            System.out.println("vt.sign = true 通知 while (!sign) 结束！");
        });

        Thread01.start();
        Thread02.start();
    }

}

class VT implements Runnable {

    public boolean sign = false;

    public void run() {
        while (!sign) {
        }
        System.out.println("你坏");
    }
}
