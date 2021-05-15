package com.jinchi.stock.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author: vhtk
 * @Description: kafka向程序输入数据的通道
 * @Date: 2021/5/15
 */
public interface NotifyDeliverySink {

    String INPUT = "notify-stock-delivery-input";

    @Input(NotifyDeliverySink.INPUT)
    SubscribableChannel input();
}
