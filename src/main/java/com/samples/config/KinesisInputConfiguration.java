package com.samples.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClientBuilder;
import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class KinesisInputConfiguration {

  @Bean
  @Primary
  public AmazonKinesisAsync amazonKinesis(AWSCredentialsProvider awsCredentialsProvider,
                                          RegionProvider regionProvider) {
    return AmazonKinesisAsyncClientBuilder.standard()
        .withCredentials(awsCredentialsProvider)
        .withRegion(
            regionProvider.getRegion()
                .getName())
        .build();
  }


  @Bean
  @Primary
  public AWSCredentialsProvider awsCredentialsProvider() {
    return new DefaultAWSCredentialsProviderChain();
  }
}
