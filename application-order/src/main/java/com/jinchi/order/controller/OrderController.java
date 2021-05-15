package com.jinchi.order.controller;

import com.jinchi.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2021/5/15
 */
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{productId}")
    public void send(@PathVariable("productId") Integer productId){
        orderService.notifyStock(productId);
    }
}
