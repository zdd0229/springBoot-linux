package com.z.core.collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Delay> delayQueue = new DelayQueue<>();
        delayQueue.offer(new Delay("aaa", 5, TimeUnit.SECONDS));
        delayQueue.offer(new Delay("ccc", 1, TimeUnit.SECONDS));
        delayQueue.offer(new Delay("bbb", 3, TimeUnit.SECONDS));

        System.out.println((delayQueue.take().getMsg()));
        System.out.println((delayQueue.take().getMsg()));
        System.out.println((delayQueue.take().getMsg()));

        System.out.println("end");

    }

    static class Delay implements Delayed {

        private String msg;
        private long time;

        public Delay(String msg, long time, TimeUnit unit) {
            this.msg = msg;
            this.time = System.currentTimeMillis() + time > 0 ? unit.toMillis(time) : 0;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Delay o1 = (Delay) o;
            long diff = time - o1.getTime();
            if (diff > 0) {
                return 1;
            } else {
                return -1;
            }
        }

        public long getTime() {
            return time;
        }

        public String getMsg() {
            return msg;
        }
    }

}
