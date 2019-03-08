package com.queue.copenfeign.callback;

import com.queue.aapi.bean.User;
import com.queue.copenfeign.service.HelloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceCallback implements HelloService {

    @Override
    public ResponseEntity<String> hello() {
        return new ResponseEntity("empty message", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> hello1(String name) {

        return new ResponseEntity("empty message,can't find ", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> hello2(User user) {

        return new ResponseEntity("empty message, find other way", HttpStatus.OK);
    }
}
