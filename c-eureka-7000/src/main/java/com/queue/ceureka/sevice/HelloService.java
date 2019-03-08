package com.queue.ceureka.sevice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errorHandler")
    public ResponseEntity<?> hello(){
        return restTemplate.getForEntity("http://provider-eureka/hello",String.class);
    }

    public ResponseEntity<?> errorHandler(){
        return new ResponseEntity<>("error", HttpStatus.OK);
    }
}
