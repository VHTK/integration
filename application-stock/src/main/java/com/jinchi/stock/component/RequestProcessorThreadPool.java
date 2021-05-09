package com.jinchi.stock.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
public class RequestProcessorThreadPool {

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();


    public RequestProcessorThreadPool() {
        RequestQueue requestQueue = RequestQueue.getInstance();

        for (int i = 0; i < 10; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }

    private static class SingletonHolder {
        private static final RequestProcessorThreadPool INSTANCE = new RequestProcessorThreadPool();
    }

    public static final RequestProcessorThreadPool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void init(){
        getInstance();
    }
}
