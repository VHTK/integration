package com.jinchi.java.base.juc;

/**
 * Created by ZHANGTAO269 on 2019-4-9.
 */
public class ThreeThreadPrint {
    public static final Object obj = new Object();
    public static volatile int cur = 1;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i = i + 3) {
                    synchronized (obj) {
                        while (cur % 3 != 1) {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(i);
                        cur = i + 1;
                        obj.notifyAll();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 2; i <= 100; i = i + 3) {
                synchronized (obj) {
                    while (cur % 3 != 2) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(i);
                    cur = i + 1;
                    obj.notifyAll();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 3; i <= 100; i = i + 3) {
                synchronized (obj) {
                    while (cur % 3 != 0) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(i);
                    cur = i + 1;
                    obj.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
