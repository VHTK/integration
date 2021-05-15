package com.jinchi.order.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author: vhtk
 * @Description: 程序向kafka输出数据的通道
 * @Date: 2021/5/15
 */
public interface NotifyDeliverySource {

    String OUTPUT = "notify-stock-delivery-output";

    @Output(NotifyDeliverySource.OUTPUT)
    MessageChannel notifyDelivery();
}
