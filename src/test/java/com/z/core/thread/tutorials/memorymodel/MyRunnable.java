package com.z.core.thread.tutorials.memorymodel;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        methodOne();
    }

    private void methodOne(){
        int localVar1 = 33;
        MySharedObject localVar2= MySharedObject.sharedInstance;

        Integer share=localVar1;

        methodTwo();
    }

    private void methodTwo(){
        Integer localVar1 = new Integer(3);
    }
}
