package com.queue.pbuskafka8500.consumer;

import com.queue.aapi.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(BusKafkaSinkSource.class)
public class BusKafkaConsumerUser {
    private Logger logger = LoggerFactory.getLogger(BusKafkaConsumerUser.class);

    @StreamListener(value = BusKafkaSinkSource.KAFKA_INPUT)
    public void receive(User user){
        logger.info(user.toString());
    }
}
