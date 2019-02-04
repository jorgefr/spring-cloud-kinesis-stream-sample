package com.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class SpringCloudKinesisStreamApplication {


  public static void main(String[] args) {
      SpringApplication.run(SpringCloudKinesisStreamApplication.class, args);
  }
}
