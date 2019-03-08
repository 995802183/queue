package com.queue.skafkamsg9100.service;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * custom kafka partitioner
 */
public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numPartitions = partitionInfos.size();
        if(keyBytes == null || ! (key instanceof String)){
            throw new InvalidRecordException("Wb exception al messages to have customer name as key");
        }
        if(key.equals("wyw")){
            return numPartitions;
        }
        return Math.abs(Utils.murmur2(keyBytes)%(numPartitions-1));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
