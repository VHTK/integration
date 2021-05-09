package com.jinchi.stock.component;


import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
public class RequestProcessorThread implements Callable<Boolean> {

    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }


    @Override
    public Boolean call() throws Exception {
        try {
            while (true) {
                Request request = queue.take();
                Boolean isForceRefresh = request.isForceRefresh();
                if (!isForceRefresh) {
                    RequestQueue requestQueue = RequestQueue.getInstance();
                    Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();
                    if (request instanceof DataUpdateRequest) {
                        flagMap.put(request.getProductId(), true);
                    } else if (request instanceof ReloadDataCacheRequest) {
                        Boolean flag = flagMap.get(request.getProductId());
                        if (flag != null && flag) {
                            flagMap.put(request.getProductId(), false);
                        }
                        // 如果是缓存刷新的请求，而且发现标识不为空，但是标识是false
                        // 说明前面已经有一个数据库更新请求 + 一个缓存刷新请求了
                        if (flag != null && !flag) {
                            return true;
                        }
                    }
                }
                request.process();
            }
        } catch (Exception e) {

        }
        return true;
    }
}
