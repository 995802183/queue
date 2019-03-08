package com.queue.skafkamsg9100.controller;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class CustomSeekController {
    private Logger logger = LoggerFactory.getLogger(CustomSeekController.class);

    @RequestMapping("/receive_seek")
    public ResponseEntity<?>receive(){
        Properties kafkaPros = new Properties();
        kafkaPros.put("bootstrap.servers","192.168.74.133:9092");
        kafkaPros.put("group.id","hello_group");
        kafkaPros.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaPros.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaPros.put("enable.auto.commit","false");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(kafkaPros);
        consumer.subscribe(Collections.singletonList("hello_topic"),new CustomSeekRebalanceListner(consumer));
        ConsumerSeekTask consumerTask = new ConsumerSeekTask(consumer);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(consumerTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    Map<TopicPartition,OffsetAndMetadata> currentOffsets = new HashMap<>();
    /**
     * custom rebalance listener
     */
    class CustomSeekRebalanceListner implements ConsumerRebalanceListener{
        private KafkaConsumer consumer ;

        public CustomSeekRebalanceListner(KafkaConsumer consumer) {
            this.consumer = consumer;
        }

        //before rebalance and after read message, next customer can know the message's offset
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            //commitTransactionDB();
        }

        //after rebalance and before read message
        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {
            for(TopicPartition topicPartition:collection){
                //consumer.seek(topicPartition,getOffsetFromDB());
            }
        }
    }

    /**
     *
     */
    class ConsumerSeekTask implements Runnable {
        private KafkaConsumer<String, String> consumer;

        public ConsumerSeekTask(KafkaConsumer<String, String> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            int count = 0;
            Duration zero = Duration.ofMillis(0);
            consumer.poll(zero);
            for(TopicPartition topicPartition : consumer.assignment()){
                //consumer.seek(topicPartition,getOffsetFromDB());
            }
            try {
                while (true) {
                    Duration duration = Duration.ofMillis(100);
                    ConsumerRecords<String, String> records = consumer.poll(duration);
                    for (ConsumerRecord<String, String> record : records) {
                        //processRecord(record);
                        //storeRecordInDb(record);
                        //storeOffsetInDB(record.topic(),record.partition(),record.offset());
                    }
                    //commitTransactionDB();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    //commit sync
                    consumer.commitSync();
                } finally {
                    consumer.close();
                }
            }
        }
    }
}
