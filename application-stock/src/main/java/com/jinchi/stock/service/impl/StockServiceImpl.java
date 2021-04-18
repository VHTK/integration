package com.jinchi.stock.service.impl;

import com.jinchi.stock.domain.entity.Stock;
import com.jinchi.stock.mapper.StockMapper;
import com.jinchi.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
public class StockServiceImpl implements StockService{

    @Autowired
    private StockMapper stockMapper;


    @Override
    @Transactional
    public int updateStock(Stock stock) {
        return stockMapper.updateByOptimistic(stock);
    }

    @Override
    public Integer queryStock(String name) {
        return stockMapper.selectByName(name);
    }
}
