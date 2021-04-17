package com.jinchi.order.service;

import com.jinchi.common.base.dto.EventTopic;
import com.jinchi.order.stream.TestProduceCmdSource;
import com.jinchi.spring.messaging.publish.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZHANGTAO269 on 2019-1-22.
 */
@Service
public class TestProduceCmdService {

    @Autowired
    private TestProduceCmdSource testProduceCmdSource;

    public void produce() {
        EventTopic eventTopic = new EventTopic();
        if (new MessagePublisher<EventTopic>().publish(testProduceCmdSource.testProduceCmd(), eventTopic)) {
            System.out.println("test produce msg success !!!");
        }
    }
}
