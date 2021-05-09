package com.jinchi.stock.service;

import com.jinchi.stock.domain.dto.ProductInfoDTO;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
public interface CacheService {

    ProductInfoDTO saveLocalCache(ProductInfoDTO dto);

    ProductInfoDTO getLocalCache(Integer productId);
}
