package com.queue.pbuskafka8500.controller;

import com.queue.aapi.bean.User;
import com.queue.pbuskafka8500.consumer.BusKafkaSinkSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusSendController {

    @Autowired
    private BusKafkaSinkSource busKafkaSinkSource;


    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public void send(String name,String age){
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":").append(age);
        Message<String> build = MessageBuilder.withPayload(sb.toString()).build();
        busKafkaSinkSource.kafkaOutput().send(build);
    }

    @RequestMapping(value="/send_body",method = RequestMethod.POST)
    public void sendUser(int id,String name ,int age ){
        User user = new User(id,name,age);
        Message<User> build = MessageBuilder.withPayload(user).build();
        busKafkaSinkSource.kafkaOutput().send(build);
    }
}
