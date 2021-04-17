package com.jinchi.java.base.future;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * guava监听future返回值
 * Created by ZHANGTAO269 on 2019-1-28.
 */
public class MoreListenableFutureTest {

    public static void main(String[] args) {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        List<Long> skuIds = Lists.newArrayList(1L, 2L, 3L);
        List<ListenableFuture<Long>> listenableFutures = new ArrayList<>();
        skuIds.forEach(skuId -> {
            ListenableFuture<Long> listenableFuture = executorService.submit(() -> {
                System.out.println("call execute.." + skuId);
                TimeUnit.SECONDS.sleep(1);
                return skuId * 10;
            });
            listenableFutures.add(listenableFuture);
        });

        listenableFutures.forEach(f ->
                Futures.addCallback(f, new FutureCallback<Long>() {
                    @Override
                    public void onSuccess(Long result) {
                        System.out.println("get listenable future's result with callback " + result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("future throw exception");
                        t.printStackTrace();
                    }
                },executorService)
        );
    }
}
