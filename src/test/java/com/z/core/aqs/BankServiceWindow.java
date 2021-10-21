package com.z.core.aqs;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class BankServiceWindow {

    private final Sync sync;

    public BankServiceWindow() {
        this.sync = new Sync();
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

    public void handle(){
        sync.acquire(1);
    }

    public void unhandle(){
        sync.release(1);
    }

    /**
     * gulimall@1524069314400653.onaliyun.com
     * ma9CD4qGcBUGraiIC)q50RtYy?!nz{NL
     */
}
