package com.jinchi.java.base.juc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockTwoThreadPrint {
    static class SoulutionTask implements Runnable {
        Map map = new ConcurrentHashMap();
        static ReentrantLock lock = new ReentrantLock();
        static int value = 0;

        @Override
        public void run() {
            while (value <= 100) {
                synchronized (SoulutionTask.class) {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + value++);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new SoulutionTask(), "偶数");
        Thread t2 = new Thread(new SoulutionTask(), "奇数");

        t1.start();
        t2.start();
    }
}