package com.queue.cconfig7600;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CConfig7600Application {

    public static void main(String[] args) {
        SpringApplication.run(CConfig7600Application.class, args);
    }

}

