package com.jinchi.stock.service;

import com.jinchi.stock.domain.entity.Stock;
import org.springframework.stereotype.Service;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
@Service
public interface StockService {

    int updateStock(Stock stock);

    Integer queryStock(String name);
}
