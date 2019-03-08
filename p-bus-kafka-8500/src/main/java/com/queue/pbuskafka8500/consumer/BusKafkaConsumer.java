package com.queue.pbuskafka8500.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(BusKafkaSinkSource.class)
public class BusKafkaConsumer {
    private Logger logger = LoggerFactory.getLogger(BusKafkaConsumer.class);

    @StreamListener(value = BusKafkaSinkSource.KAFKA_INPUT)
    public void receive(String message){
        logger.info(message);
    }
}
