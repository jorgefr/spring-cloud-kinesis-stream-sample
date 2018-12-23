package com.samples.kinesis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableBinding(Source.class)
@RequiredArgsConstructor
public class KinesisProducer {

  private final Source source;

  public void send() {
    log.info("sending message");
    //source.output().send(MessageBuilder.withPayload("message").build());
  }

}
