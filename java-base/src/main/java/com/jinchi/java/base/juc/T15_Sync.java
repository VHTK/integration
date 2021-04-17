package com.jinchi.java.base.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T15_Sync {
    private int count1 = 10;
    private int count2 = 10;

    public void m1() {
        synchronized (this) {
            count1--;
            System.out.print("count = " + count1 + "   ");
        }
    }

    public synchronized void m2() {
        count2--;                            // 等价 synchronized (this)
        System.out.print("count = " + count2 + "   ");
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        T15_Sync t = new T15_Sync();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(t::m1, "t" + i + "m1"));
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("m1结束！！！");


        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(t::m2, "t" + i + "m2"));
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("m2结束！！！");
    }
}