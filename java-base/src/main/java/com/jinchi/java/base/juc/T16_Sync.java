package com.jinchi.java.base.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T16_Sync {
    private static int count3 = 10;
    private static int count4 = 10;

    public static void m3() {
        synchronized (T16_Sync.class) {
            count3--;
            System.out.print("count = " + count3 + "   ");
        }
    }

    public synchronized static void m4() {   // 等价 synchronized (T01_Synchronized.class)
        count4--;
        System.out.print("count = " + count4 + "   ");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(() -> {
                T16_Sync.m3();
            }, "t" + i + "m3"));
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("m3结束！！！");

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(() -> {
                T16_Sync.m4();
            }, "t" + i + "m4"));
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("m4结束！！！");
    }
}