package com.jinchi.stock.controller;

import com.jinchi.stock.domain.dto.ProductInfoDTO;
import com.jinchi.stock.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @PostMapping("/cache")
    public String putCache(@RequestBody ProductInfoDTO productInfoDTO) {
        cacheService.saveLocalCache(productInfoDTO);
        return "success";
    }

    @GetMapping("/cache")
    public String getCache(@RequestParam Integer id) {
        ProductInfoDTO dto = cacheService.getLocalCache(id);
        return dto.getName();
    }
}
