package com.jinchi.stock.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TestProduceCmdSink {

	String INPUT = "testProduceCmdChannel";

	@Input(TestProduceCmdSink.INPUT)
    SubscribableChannel input();
}
