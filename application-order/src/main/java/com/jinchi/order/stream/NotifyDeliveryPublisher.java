package com.jinchi.order.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(NotifyDeliverySource.class)
public class NotifyDeliveryPublisher {
}
