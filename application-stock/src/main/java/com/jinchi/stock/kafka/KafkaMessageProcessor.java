package com.jinchi.stock.kafka;

import com.jinchi.common.base.util.JsonUtil;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class KafkaMessageProcessor implements Runnable {

    private KafkaStream kafkaStream;
 
    public KafkaMessageProcessor(KafkaStream kafkaStream) {
        this.kafkaStream = kafkaStream;
    }
 
    public void run() {
        ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
        while (it.hasNext()) {
        	String message = new String(it.next().message());

        	// 将message转换成json对象
            ProductUpdateDto dto = JsonUtil.fromJson(message, ProductUpdateDto.class);
            if("productInfoService".equals(dto.getServiceId())){

            }
        }
    }

}