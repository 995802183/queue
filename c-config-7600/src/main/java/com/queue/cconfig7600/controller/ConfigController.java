package com.queue.cconfig7600.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${name}")
    private String name;

    @RequestMapping("/name")
    public ResponseEntity<?>getName(){
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
}
