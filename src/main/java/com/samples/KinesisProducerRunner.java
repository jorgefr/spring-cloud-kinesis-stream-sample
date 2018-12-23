package com.samples;

import com.samples.kinesis.KinesisProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class KinesisProducerRunner implements CommandLineRunner {
  @Autowired
  KinesisProducer producer;

  @Override
  public void run(String... args) throws Exception {
    producer.send();
  }
}
