package com.queue.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SEureka9000Application {

    public static void main(String[] args) {
        SpringApplication.run(SEureka9000Application.class, args);
    }

}

