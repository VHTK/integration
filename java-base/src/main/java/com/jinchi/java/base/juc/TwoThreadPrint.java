package com.jinchi.java.base.juc;

public class TwoThreadPrint {
    static class SoulutionTask implements Runnable {
        static int value = 0;

        @Override
        public void run() {
            while (value <= 100) {
                synchronized (SoulutionTask.class) {
                    System.out.println("当前线程" + Thread.currentThread().getName());
                    Thread t1 = getThreadByName("偶数");
                    System.out.println("偶数" + t1.getState());

                    Thread t2 = getThreadByName("奇数");
                    System.out.println("奇数" + t2.getState());

                    System.out.println(Thread.currentThread().getName() + ":" + value++);
                    SoulutionTask.class.notify();
                    System.out.println("偶数" + t1.getState());
                    System.out.println("奇数" + t2.getState());
                    try {
                        Thread.sleep(100);
                        System.out.println("notify之后唤醒其他线程，其他线程运行前必须等待当前线程wait来释放锁");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        SoulutionTask.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * t1,t2的顺序是随机的
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(new SoulutionTask(), "偶数");
        t1.setPriority(Thread.MIN_PRIORITY);
        Thread t2 = new Thread(new SoulutionTask(), "奇数");
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }

    public static Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) {
                return t;
            }
        }
        return null;
    }
}