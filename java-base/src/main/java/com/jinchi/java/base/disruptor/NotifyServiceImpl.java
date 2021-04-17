package com.jinchi.java.base.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class NotifyServiceImpl implements NotifyService, DisposableBean, InitializingBean {
    private Disruptor<LongEvent> disruptor;
    private static final int RING_BUFFER_SIZE = 1024 * 1024;

    @Override
    public void destroy() throws Exception {
        disruptor.shutdown();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        disruptor = new Disruptor<>(new LongEventFactory(), RING_BUFFER_SIZE, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new NotifyEventHandlerException());
        disruptor.handleEventsWith(new NotifyEvent1Handler(), new NotifyEvent2Handler());
        disruptor.start();
    }


    @Override
    public void sendNotify(String message) {
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//        ringBuffer.publishEvent(new EventTranslatorOneArg<NotifyEvent,  String>() {
//            @Override
//            public void translateTo(NotifyEvent stream, long sequence, String data) {
//                stream.setMessage(data);
//            }
//        }, message);
        ringBuffer.publishEvent((event, sequence, data) -> event.set(data), message); //lambda式写法，如果是用jdk1.8以下版本使用以上注释的一段
    }
}