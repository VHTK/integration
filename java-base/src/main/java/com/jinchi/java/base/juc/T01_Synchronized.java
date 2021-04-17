package com.jinchi.java.base.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized的各种姿势
 */
public class T01_Synchronized {
    private int count1 = 10;
    private int count2 = 10;
    private static int count3 = 10;
    private static int count4 = 10;
    private int count5 = 10;
    private ReentrantLock lock = new ReentrantLock();

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

    public static void m3() {
        synchronized (T01_Synchronized.class) {
            count3--;
            System.out.print("count = " + count3 + "   ");
        }
    }

    public synchronized static void m4() {   // 等价 synchronized (T01_Synchronized.class)
        count4--;
        System.out.print("count = " + count4 + "   ");
    }

    public void m5() {
        try {
            lock.lock();
            count5--;
            System.out.print("count = " + count5 + "   ");
        } catch (Exception e) {
            // aspect error
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        T01_Synchronized t = new T01_Synchronized();
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

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(() -> {
                T01_Synchronized.m3();
            }, "t" + i + "m3"));
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("m3结束！！！");

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(() -> {
                T01_Synchronized.m4();
            }, "t" + i + "m4"));
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("m4结束！！！");

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(t::m5, "t" + i + "m5"));
        }

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("m5结束！！！");
    }
}