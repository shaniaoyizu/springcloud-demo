package com.abc.controller;

import com.abc.producer.SomeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunbc on 2020-02-25
 */
@RestController
public class SomeController {

    @Autowired
    private SomeProducer producer;

    @PostMapping("/msg/send")
    public String sendHandler(@RequestParam("message") String msg){
        return producer.sendMessage(msg);
    }
}
