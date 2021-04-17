package com.jinchi.spring.messaging.subcribe;

import com.jinchi.common.base.util.JsonUtil;
import com.jinchi.spring.messaging.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class MessageSubscriber<T> {
	private static Logger log = LoggerFactory.getLogger(MessageSubscriber.class);

	public Result<T> getMessage(Message<Result<T>> message) {
		String uid = (String) message.getHeaders().get("uid");
		log.info("MQ:Received message(id={})", uid);
		Result<T> payload = message.getPayload();
		log.info("MQ:Message content is {}", JsonUtil.toJson(payload));
		return payload;
	}
}
