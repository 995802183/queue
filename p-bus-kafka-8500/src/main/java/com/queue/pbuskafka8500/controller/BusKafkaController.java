package com.queue.pbuskafka8500.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class BusKafkaController {

    @Value("${name}")
    private String name;

    @RequestMapping("/name")
    public ResponseEntity<?> getName(){
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
}
