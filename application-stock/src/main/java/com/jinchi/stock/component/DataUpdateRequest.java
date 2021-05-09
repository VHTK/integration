package com.jinchi.stock.component;

import com.jinchi.stock.service.StockService;

/**
 * @Author: vhtk
 * @Description:
 * 1。删除缓存
 * 2，更新数据库
 *
 * @Date: 2021/4/18
 */
public class DataUpdateRequest implements Request {

    private InventoryDto inventoryDto;

    private StockService stockService;

    public  DataUpdateRequest(StockService stockService, InventoryDto inventoryDto) {
        this.stockService = stockService;
        this.inventoryDto = inventoryDto;
    }


    @Override
    public void process() {
        // 删除缓存
        stockService.removeStockCache(inventoryDto);

        // 修改数据库
        stockService.updateInventory(inventoryDto);
    }

    @Override
    public Integer getProductId() {
        return inventoryDto.getProdunctId();
    }

    @Override
    public Boolean isForceRefresh() {
        return false;
    }
}
