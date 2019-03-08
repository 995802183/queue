package com.queue.copenfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class COpenfeign7500Application {

    public static void main(String[] args) {
        SpringApplication.run(COpenfeign7500Application.class, args);
    }

}

