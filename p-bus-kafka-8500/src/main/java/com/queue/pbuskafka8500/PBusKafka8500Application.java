package com.queue.pbuskafka8500;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PBusKafka8500Application {

    public static void main(String[] args) {
        SpringApplication.run(PBusKafka8500Application.class, args);
    }

}

