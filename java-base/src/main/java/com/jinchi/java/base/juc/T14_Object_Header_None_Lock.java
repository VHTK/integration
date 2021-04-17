package com.jinchi.java.base.juc;

import org.openjdk.jol.info.ClassLayout;

/**
 * 测试锁升级   -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 */
public class T14_Object_Header_None_Lock {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        System.out.println("before lock");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println("locking...........");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}