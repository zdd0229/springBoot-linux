package com.z.core.concurrency.sync;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class SyncTest {

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        Object object = new Object();
        System.out.println(object + " 十六进制hash：" + Integer.toHexString(object.hashCode()));
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

}
