package com.jinchi.stock.service.impl;

import com.jinchi.stock.domain.dto.ProductInfoDTO;
import com.jinchi.stock.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
@Service
public class CacheServiceImpl implements CacheService{
    public static final String CACHE_NAME = "local";

    @Override
    @CachePut(value = CACHE_NAME, key = "'key_'+#productInfoDTO.getId()")
    public ProductInfoDTO saveLocalCache(ProductInfoDTO productInfoDTO) {
        return productInfoDTO;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'key_'+#productId")
    public ProductInfoDTO getLocalCache(Integer productId) {
        return null;
    }
}
