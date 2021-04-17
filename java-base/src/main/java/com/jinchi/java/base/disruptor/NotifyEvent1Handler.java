package com.jinchi.java.base.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

public class NotifyEvent1Handler implements EventHandler<LongEvent>,WorkHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("handler1接收到消息");
        this.onEvent(event);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        System.out.println(event+">>>"+ UUID.randomUUID().toString());
    }
}