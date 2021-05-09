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
public class ReloadDataCacheRequest implements Request {

    private InventoryDto inventoryDto;

    private StockService stockService;

    private Boolean forceRefresh;

    public  ReloadDataCacheRequest(StockService stockService, InventoryDto inventoryDto, Boolean forceRefresh) {
        this.stockService = stockService;
        this.inventoryDto = inventoryDto;
        this.forceRefresh = forceRefresh;
    }


    @Override
    public void process() {
        // 从数据库查询商品库存
       Integer stock=  stockService.queryStock(inventoryDto.getProdunctId().toString());

        // 将库存重新加载到缓存
        stockService.reloadCache(stock);
    }

    @Override
    public Integer getProductId() {
        return inventoryDto.getProdunctId();
    }

    @Override
    public Boolean isForceRefresh() {
        return forceRefresh;
    }

    public Boolean getForceRefresh() {
        return forceRefresh;
    }

    public void setForceRefresh(Boolean forceRefresh) {
        this.forceRefresh = forceRefresh;
    }
}
