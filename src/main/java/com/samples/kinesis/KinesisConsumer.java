package com.samples.kinesis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
@EnableBinding(Sink.class)
public class KinesisConsumer {

    @StreamListener(Sink.INPUT)
    public void processCommand(@Payload String inputMessage) {
      log.debug("Received message: {}", inputMessage);
    }

}
