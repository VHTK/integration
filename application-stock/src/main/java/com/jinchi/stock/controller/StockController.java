package com.jinchi.stock.controller;

import com.jinchi.spring.messaging.result.Result;
import com.jinchi.stock.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/17
 */
@RestController
public class StockController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StockMapper stockMapper;

    @GetMapping("/stock")
    public Result<Integer> queryStock(@RequestParam String name){
        redisTemplate.opsForValue().set("key1","value1");
        return new Result<Integer>().setResult(stockMapper.selectByName(name));
    }
}
