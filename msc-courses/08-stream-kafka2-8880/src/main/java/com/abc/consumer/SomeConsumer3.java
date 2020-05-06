package com.abc.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * Created by sunbc on 2020-03-04
 */
@Component
@EnableBinding(Sink.class)
public class SomeConsumer3 {

    @StreamListener(Sink.INPUT)
    public void printMessage(Object msg){
        System.out.println(msg);
    }
}
