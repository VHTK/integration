package com.jinchi.order.service;

import com.jinchi.common.base.dto.EventTopic;
import com.jinchi.order.stream.NotifyDeliverySource;
import com.jinchi.spring.messaging.publish.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZHANGTAO269 on 2019-1-22.
 */
@Service
public class OrderService {

    @Autowired
    private NotifyDeliverySource notifyDeliverySource;

    public void notifyStock(Integer productId) {
        EventTopic eventTopic = new EventTopic();
        eventTopic.setProductId(productId);
        if (new MessagePublisher<EventTopic>().publish(notifyDeliverySource.notifyDelivery(), eventTopic)) {
            System.out.println("test produce msg success !!!");
        }
    }
}
