package com.jinchi.java.base.disruptor;

import com.lmax.disruptor.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotifyEventHandlerException implements ExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(NotifyEventHandlerException.class);

    @Override
    public void handleEventException(Throwable throwable, long sequence, Object event) {
        throwable.fillInStackTrace();
        logger.error("process data error sequence ==[{}] stream==[{}] ,ex ==[{}]", sequence, event.toString(), throwable.getMessage());
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
        logger.error("start disruptor error ==[{}]!", throwable.getMessage());
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
        logger.error("shutdown disruptor error ==[{}]!", throwable.getMessage());
    }
}