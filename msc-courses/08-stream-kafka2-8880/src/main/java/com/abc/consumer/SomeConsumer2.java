package com.abc.consumer;

import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Created by sunbc on 2020-03-04
 */
//@Component
//@EnableBinding(Sink.class)
public class SomeConsumer2 {

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void printMessage(Object msg){
        System.out.println(msg);
    }
}
