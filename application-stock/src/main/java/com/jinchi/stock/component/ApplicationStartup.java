package com.jinchi.stock.component;

import com.jinchi.stock.kafka.KafkaConsumer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
 
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 初始化完成后. 执行一次同步系统参数
        RequestProcessorThreadPool.init();
        new Thread(new KafkaConsumer("topic")).start();
    }
}