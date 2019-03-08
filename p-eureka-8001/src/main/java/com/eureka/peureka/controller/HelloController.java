package com.eureka.peureka.controller;

import com.queue.aapi.bean.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity<?> hello() {

        return new ResponseEntity<>("world2", HttpStatus.OK);
    }

    @RequestMapping("/hello1")
    public ResponseEntity<?> hello1(@RequestParam String name){
        return new ResponseEntity<>("hello:"+name,HttpStatus.OK);
    }

    @RequestMapping("/hello2")
    public ResponseEntity<?> hello2(@RequestBody User user){
        return new ResponseEntity<>("hello:"+user.getName()+"."+user.getAge(),HttpStatus.OK);
    }

}
