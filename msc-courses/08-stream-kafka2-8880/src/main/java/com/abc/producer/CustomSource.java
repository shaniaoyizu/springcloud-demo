package com.abc.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by sunbc on 2020-03-04
 */
public interface CustomSource {

    String CHANNEL_NAME = "xxx";

    @Output(CustomSource.CHANNEL_NAME)
    MessageChannel output();
}
