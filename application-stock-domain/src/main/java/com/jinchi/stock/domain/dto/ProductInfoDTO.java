package com.jinchi.stock.domain.dto;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/4/18
 */
public class ProductInfoDTO {
    private Integer id;
    private String name;
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
