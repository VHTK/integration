package com.jinchi.stock.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
public class RequestQueue {
    /**
     * 内存队列
     */
    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    private Map<Integer,Boolean> flagMap = new ConcurrentHashMap<>();

    public int queueSize() {
        return queues.size();
    }

    public ArrayBlockingQueue<Request> getQueue(Integer index) {
        return queues.get(index);
    }

    private static class SingletonHolder {
        private static final RequestQueue INSTANCE = new RequestQueue();
    }

    public static final RequestQueue getInstance() {
        return RequestQueue.SingletonHolder.INSTANCE;
    }

    public void addQueue(ArrayBlockingQueue queue) {
        queues.add(queue);
    }

    public  Map<Integer,Boolean> getFlagMap(){
        return flagMap;
    }
}
