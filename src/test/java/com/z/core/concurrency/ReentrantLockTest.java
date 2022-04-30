package com.z.core.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            new Thread(new Increase()).start();
        }
    }

    static class Increase implements Runnable {

        private static int j = 0;

        private static ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {

            lock.lock();

            for (int k = 0; k < 10000; k++) {
                j++;
            }
            System.out.println(j);

            lock.unlock();
        }
    }

}
