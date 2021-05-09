package com.jinchi.stock.component;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
public interface Request {

    void process();

    Integer getProductId();

    Boolean isForceRefresh();
}
