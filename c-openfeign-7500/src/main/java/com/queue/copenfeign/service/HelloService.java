package com.queue.copenfeign.service;


import com.queue.aapi.bean.User;
import com.queue.copenfeign.callback.HelloServiceCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="provider-eureka",fallback = HelloServiceCallback.class)
public interface HelloService {

    @RequestMapping("/hello")
    ResponseEntity<String> hello();

    @RequestMapping("/hello1")
    ResponseEntity<String> hello1(@RequestParam("name") String name);

    @RequestMapping("/hello2")
    ResponseEntity<String> hello2(@RequestBody User user);
}
