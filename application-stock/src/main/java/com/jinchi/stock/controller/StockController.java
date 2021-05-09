package com.jinchi.stock.controller;

import com.jinchi.spring.messaging.result.Result;
import com.jinchi.stock.component.DataUpdateRequest;
import com.jinchi.stock.component.InventoryDto;
import com.jinchi.stock.component.ReloadDataCacheRequest;
import com.jinchi.stock.component.RequestAsyncProcessService;
import com.jinchi.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/17
 */
@RestController
public class StockController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StockService stockService;

    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;

    @GetMapping("/stock")
    public Result<Integer> queryStock(@RequestParam String name) {
        redisTemplate.opsForValue().set("key1", "value1");
        return new Result<Integer>().setResult(stockService.queryStock(name));
    }

    @PostMapping("/cache/stock")
    public Result<Integer> queryCacheStock(@RequestBody InventoryDto inventoryDto) {
        try {
            requestAsyncProcessService.process(new ReloadDataCacheRequest(stockService, inventoryDto,false));
            long startTime = System.currentTimeMillis();
            long waitTime = 0L;
            long endTime = 0L;
            while (true) {
                // 如果超过200ms没有获取到数据，可以尝试从数据库获取数据
                if (waitTime > 200) {
                    break;
                }

                Integer stock = stockService.queryCacheStock(inventoryDto.getProdunctId().toString());
                if (Objects.nonNull(stock)) {
                    return new Result<Integer>().setResult(stock);
                } else {
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }

            Integer stock = stockService.queryStock(inventoryDto.getProdunctId().toString());
            if(Objects.nonNull(stock)){
                // 将缓存刷新一下
                requestAsyncProcessService.process(new ReloadDataCacheRequest(stockService, inventoryDto,true));

                // 代码运行到这里只有三种情况
                // 1 上次也是读请求，数据刷到redis，但是redis lru 算法清理掉了
                // 2 可能请求200ms内，读请求在队列中一直积压着，没有等待到它执行(生产环境基本要扩容了)
                // 3 数据库本身没有，缓存穿透，穿透redis，请求到达redis

                return new Result<Integer>().setResult(stock);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result<Integer>().setResult(-1);
    }

    @PostMapping("/stock")
    public Result<Integer> reloadStock(@RequestBody InventoryDto inventoryDto) {
        DataUpdateRequest dataUpdateRequest = new DataUpdateRequest(stockService, inventoryDto);
        requestAsyncProcessService.process(dataUpdateRequest);
        return new Result<Integer>();
    }
}
