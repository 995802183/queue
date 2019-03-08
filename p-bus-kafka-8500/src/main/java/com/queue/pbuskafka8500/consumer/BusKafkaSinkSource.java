package com.queue.pbuskafka8500.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface BusKafkaSinkSource {

    String KAFKA_INPUT = "kafka_input";
    @Input(BusKafkaSinkSource.KAFKA_INPUT)
    SubscribableChannel kafkaInput();

    String KAFKA_OUTPUT = "kafka_output";
    @Output(BusKafkaSinkSource.KAFKA_OUTPUT)
    MessageChannel kafkaOutput();

}
