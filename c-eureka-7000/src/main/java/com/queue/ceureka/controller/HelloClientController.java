package com.queue.ceureka.controller;

import com.queue.ceureka.sevice.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloClientController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public ResponseEntity<?> hello(){
        ResponseEntity<String> entity = restTemplate.getForEntity("http://provider-eureka/hello", String.class);
        return entity;
    }

    @RequestMapping("/helloerror")
    public ResponseEntity<?> helloError(){
        return helloService.hello();
    }
}
