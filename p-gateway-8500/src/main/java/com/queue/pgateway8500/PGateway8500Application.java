package com.queue.pgateway8500;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class PGateway8500Application {

    public static void main(String[] args) {
        SpringApplication.run(PGateway8500Application.class, args);
    }

}

