package com.jinchi.stock.service.impl;

import com.jinchi.stock.domain.dto.ProductInfoDTO;
import com.jinchi.stock.domain.dto.ShopInfoDTO;
import com.jinchi.stock.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
@Service
public class CacheServiceImpl implements CacheService {
    public static final String CACHE_NAME = "local";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @CachePut(value = CACHE_NAME, key = "'key_'+#productInfoDTO.productId()")
    public ProductInfoDTO saveLocalCache(ProductInfoDTO productInfoDTO) {
        return productInfoDTO;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'key_'+#productId")
    public ProductInfoDTO getLocalCache(Integer productId) {
        return null;
    }

    @Override
    public ProductInfoDTO getProductInfo(Integer id) {
        // TODO
        // 现在redis缓存中获取数据
        // 如果redis缓存数据为空，则从本地缓存中获取数据
        // 如果本地缓存数据为空，则从数据库获取数据
        // 加载数据到redis缓存
        ProductInfoDTO dto = new ProductInfoDTO();
        dto.setProductId(1);
        dto.setProductName("iPhone 12 mini");
        dto.setProductPictureList("www.baidu.com");
        dto.setProductService("10086");
        dto.setProductColor("白色");
        dto.setProductSize("4.7");
        dto.setProductPrice(5999D);
        return dto;
    }

    @Override
    public ShopInfoDTO getShopInfo(Integer id) {
        ShopInfoDTO dto = new ShopInfoDTO();
        dto.setShopId(2);
        dto.setShopName("苹果官方旗舰店");
        dto.setShopLevel(2);
        dto.setShopGoodCommentRate(9.9D);
        return dto;
    }
}
