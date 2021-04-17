package com.jinchi.java.base.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *  LockSupport 类型wait notify，可以显式调用
 */
public class T13_TestLockSupport {
    public static void main(String[] args) {
        //使用lombda表达式创建一个线程t
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    //调用LockSupport的park()方法阻塞当前线程t
                    LockSupport.park();
                }
                if (i == 8) {
                    //调用LockSupport的park()方法阻塞当前线程t
                    LockSupport.park();
                }
                try {
                    //使当前线程t休眠1秒
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //启动当前线程t
        t.start();
        //唤醒线程t
        LockSupport.unpark(t);
    }
}