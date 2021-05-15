package com.jinchi.stock.stream;

import com.jinchi.common.base.dto.EventTopic;
import com.jinchi.spring.messaging.result.Result;
import com.jinchi.spring.messaging.subcribe.MessageSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@EnableBinding(NotifyDeliverySink.class)
public class NotifyDeliverySubscriber {
    private static Logger log = LoggerFactory.getLogger(NotifyDeliverySubscriber.class);

    @StreamListener(target = NotifyDeliverySink.INPUT)
    public void processStock(Message<Result<EventTopic>> message) throws Exception {
        Result<EventTopic> msg = new MessageSubscriber<EventTopic>().getMessage(message);
        try {
            EventTopic eventTopic = msg.getResult();
            System.out.println(eventTopic.getProductId());
            //throw new RuntimeException("测试异常重试");
        } catch (RuntimeException e) {
            log.error("Fail to handle message", e);
            throw e;
        }
    }

    /**
     * 消息消费失败的降级处理逻辑
     *
     * @param message
     */
    @ServiceActivator(inputChannel = "notify-stock-delivery.stream-order-stock-group.errors")
    public void error(Message<?> message) {
        log.info("Message consumer failed, call fallback!");
    }
}