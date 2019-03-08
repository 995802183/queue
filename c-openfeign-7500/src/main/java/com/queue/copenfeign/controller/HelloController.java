package com.queue.copenfeign.controller;

import com.queue.aapi.bean.User;
import com.queue.copenfeign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public ResponseEntity<?> hello(){
        return helloService.hello();
    }

    @RequestMapping("/helloOther")
    public ResponseEntity<?> helloOther(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(helloService.hello1("wangyongwen")).append("\n");
        stringBuilder.append(helloService.hello2(new User(1,"wangyongwen",29))).append("\n");
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }
}
