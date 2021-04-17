package com.jinchi.order.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestProduceCmdSource {

    @Output("testProduceCmdChannel")
    MessageChannel testProduceCmd();
}
