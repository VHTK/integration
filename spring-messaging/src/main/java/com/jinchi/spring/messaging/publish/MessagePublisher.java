package com.jinchi.spring.messaging.publish;

import com.jinchi.common.base.util.JsonUtil;
import com.jinchi.spring.messaging.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Objects;
import java.util.UUID;

public class MessagePublisher<T> {
    private static Logger log = LoggerFactory.getLogger(MessagePublisher.class);

    public boolean publish(MessageChannel channel, T payload) {
        if (Objects.isNull(payload)) {
            log.warn("MQ:Payload is null");
            return false;
        }
        String uid = UUID.randomUUID().toString();
        Result<T> result = new Result<T>().setSuccess(payload);
        Message<Result<T>> message = MessageBuilder.withPayload(result).setHeader("uid", uid).build();
        boolean sent = channel.send(message);
        if (sent) {
            log.info("MQ:Published {} message(id={}), and the content is {}", payload.getClass().getSimpleName(), uid,
                    JsonUtil.toJson(result));
        } else {
            log.info("MQ:Publish message failed, and the content is {}",
                    JsonUtil.toJson(result));
        }
        return sent;

    }

    public boolean publish(MessageChannel channel, String code, String rMsg, T payload) {
        String uid = UUID.randomUUID().toString();
        Result<T> result = new Result<T>().setFail(code, rMsg, payload);
        Message<Result<T>> message = MessageBuilder.withPayload(result).setHeader("uid", uid).build();
        boolean sent = channel.send(message);
        if (sent) {
            log.info("MQ:Published exception message(id={}), code is {}, exception message is {}, content is {}", uid,
                    code, rMsg, JsonUtil.toJson(result));
        } else {
            log.info("MQ:Publish message failed, code is {}, exception message is {}, content is {}", uid, code, rMsg,
                    JsonUtil.toJson(result));
        }
        return sent;
    }
}
