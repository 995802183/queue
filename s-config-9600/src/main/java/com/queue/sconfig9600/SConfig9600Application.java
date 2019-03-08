package com.queue.sconfig9600;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class SConfig9600Application {

    public static void main(String[] args) {
        SpringApplication.run(SConfig9600Application.class, args);
    }

}

