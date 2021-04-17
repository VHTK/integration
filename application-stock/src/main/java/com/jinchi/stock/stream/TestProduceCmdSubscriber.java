package com.jinchi.stock.stream;

import com.jinchi.common.base.dto.EventTopic;
import com.jinchi.spring.messaging.result.Result;
import com.jinchi.spring.messaging.subcribe.MessageSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@EnableBinding(TestProduceCmdSink.class)
public class TestProduceCmdSubscriber {
	private static Logger log = LoggerFactory.getLogger(MessageSubscriber.class);

	@StreamListener(target = TestProduceCmdSink.INPUT)
	public void processDcmParseCmd(Message<Result<EventTopic>> message) throws Exception {
		Result<EventTopic> msg = new MessageSubscriber<EventTopic>().getMessage(message);
		try {
			// TODO 消费消息
			System.out.println(msg.getTypedResult());
		} catch (RuntimeException e) {
			log.error("Fail to handle message", e);
			throw e;
		}
	}
}	
