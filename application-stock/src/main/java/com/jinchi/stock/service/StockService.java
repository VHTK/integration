package com.jinchi.stock.service;

import com.jinchi.stock.component.InventoryDto;
import com.jinchi.stock.domain.entity.Stock;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
public interface StockService {

    int updateStock(Stock stock);

    int updateInventory(InventoryDto inventoryDto);

    Integer queryStock(String name);

    void removeStockCache(InventoryDto inventoryDto);

    void reloadCache(Integer stock);

    Integer queryCacheStock(String name);

}
