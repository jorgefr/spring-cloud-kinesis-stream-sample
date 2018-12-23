package com.samples;

import com.samples.config.ToExcludeFromAppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {
    @ComponentScan.Filter(value = ToExcludeFromAppContext.class),
    @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
    @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)
})
public class SpringCloudKinesisStreamApplication {


  public static void main(String[] args) {
      SpringApplication.run(SpringCloudKinesisStreamApplication.class, args);
  }
}
