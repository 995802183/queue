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
public class CustomController {
    private Logger logger = LoggerFactory.getLogger(CustomController.class);
    @RequestMapping("/send")
    public ResponseEntity<?> send(){
        Properties kafkaPros = new Properties();
        kafkaPros.put("bootstrap.servers","192.168.74.133:9092");
        kafkaPros.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaPros.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(kafkaPros);
        ProducerRecord<String,String>record = new ProducerRecord<>("hello_topic",1,"key_hello","value_hello");
        //send synchronize
        RecordMetadata metadata = null;
        try {
            metadata = producer.send(record).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //send asynchronize
//        producer.send(record, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                e.printStackTrace();
//            }
//        });
        return new ResponseEntity<>(metadata.toString(), HttpStatus.OK);
    }

    @RequestMapping("/receive")
    public ResponseEntity<?>receive(){
        Properties kafkaPros = new Properties();
        kafkaPros.put("bootstrap.servers","192.168.74.133:9092");
        kafkaPros.put("group.id","hello_group");
        kafkaPros.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaPros.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaPros.put("enable.auto.commit","false");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(kafkaPros);
        consumer.subscribe(Collections.singletonList("hello_topic"),new CustomRebalanceListner(consumer));
        ConsumerTask consumerTask = new ConsumerTask(consumer);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(consumerTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    Map<TopicPartition,OffsetAndMetadata> currentOffsets = new HashMap<>();
    /**
     * custom rebalance listener
     */
    class CustomRebalanceListner implements ConsumerRebalanceListener{
        private KafkaConsumer consumer ;

        public CustomRebalanceListner(KafkaConsumer consumer) {
            this.consumer = consumer;
        }

        //before rebalance and after read message, next customer can know the message's offset
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            logger.info("lost partitions in rebalance . committing current offsets:"+currentOffsets);
            consumer.commitSync(currentOffsets);
        }

        //after rebalance and before read message
        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {

        }
    }

    /**
     *
     */
    class ConsumerTask implements Runnable {
        private KafkaConsumer<String, String> consumer;

        public ConsumerTask(KafkaConsumer<String, String> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            int count = 0;
            try {
                while (true) {
                    Duration duration = Duration.ofMillis(100);
                    ConsumerRecords<String, String> records = consumer.poll(duration);
                    for (ConsumerRecord<String, String> record : records) {
                        logger.info(record.toString());
                        //current handle record
                        currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                        // commit per 1000 records
                        if (count % 1000 == 0) {
                            consumer.commitAsync(currentOffsets, new OffsetCommitCallback() {
                                @Override
                                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                                    logger.error("commit error for offsets{}", map, e);
                                }
                            });
                        }
                    }
                    //commit async
                    consumer.commitAsync(new OffsetCommitCallback() {
                        @Override
                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                            logger.error("commit error for offsets{}", map, e);
                        }
                    });
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
