package com.jinchi.stock.domain.dto;

import lombok.Data;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
@Data
public class ProductInfoDTO {
    private Integer productId;
    private String productName;
    private String productPictureList;
    private String productService;
    private String productColor;
    private String productSize;
    private Double productPrice;
}
