package com.jinchi.stock.service.impl;

import com.jinchi.stock.component.InventoryDto;
import com.jinchi.stock.domain.entity.Stock;
import com.jinchi.stock.mapper.StockMapper;
import com.jinchi.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    @Transactional
    public int updateStock(Stock stock) {
        return stockMapper.updateByOptimistic(stock);
    }

    @Override
    public int updateInventory(InventoryDto inventoryDto) {
        return 0;
    }

    @Override
    public Integer queryStock(String name) {
        return stockMapper.selectByName(name);
    }

    @Override
    public void removeStockCache(InventoryDto inventoryDto) {
        redisTemplate.delete("key");
    }

    @Override
    public void reloadCache(Integer stock) {
        redisTemplate.opsForValue().set("key",stock);
    }

    @Override
    public Integer queryCacheStock(String name) {
      Object o = redisTemplate.opsForValue().get("key");
      return (Integer)o;
    }
}
