package com.jinchi.stock.component;

import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
@Service
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {


    @Override
    public void process(Request request) {
        try {
            // 路由
            ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());

            // 将请求放入队列
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();

        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        int index = (requestQueue.queueSize() - 1) ^ hash;
        return requestQueue.getQueue(index);
    }
}
