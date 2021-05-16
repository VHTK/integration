package com.jinchi.stock.domain.dto;

import lombok.Data;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
@Data
public class ShopInfoDTO {
    private Integer shopId;
    private String shopName;
    private Integer shopLevel;
    private Double shopGoodCommentRate;
}