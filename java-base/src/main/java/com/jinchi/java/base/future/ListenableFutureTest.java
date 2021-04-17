package com.jinchi.java.base.future;

import com.google.common.util.concurrent.*;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.concurrent.*;

/**
 * guava监听future返回值
 * Created by ZHANGTAO269 on 2019-1-28.
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        System.out.println("测试方法");
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("async-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(3000), threadFactory);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(threadPoolExecutor);
        com.google.common.util.concurrent.ListenableFuture<Integer> future = listeningExecutorService.submit(() -> {
            System.out.println("TODO");
            return Integer.MIN_VALUE;
        });

        // 方法一：静态方法添加回调
        Futures.addCallback(future, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
                System.out.println(Thread.currentThread().getName() + "@" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(Thread.currentThread().getName() + "@" + t.getMessage());
            }
        }, threadPoolExecutor);

        // 方法二：监听listenableFuture
        ListenableFutureTask<String> task = new ListenableFutureTask<String>(() -> {
            Thread.sleep(5000); // 模拟耗时操作
            return "success";
        });
        task.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("调用失败");
            }

            @Override
            public void onSuccess(String s) {
                System.out.println("调用成功：" + s);
            }
        });
        threadPoolExecutor.submit(task);
    }
}
