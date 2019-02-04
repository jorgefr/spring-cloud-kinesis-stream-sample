package com.samples.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.cloud.aws.core.region.StaticRegionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("output")
public class KinesisOutputConfiguration {
  @Value("${com.samples.aws.iam.role.arn}")
  private String rrsIamRoleArn;
  @Value("${com.samples.aws.sts.assumeRole.sessionName}")
  private String sessionName;
  @Value("${cloud.aws.region.static}")
  private String region;

  @Bean(destroyMethod = "shutdown")
  public AmazonKinesisAsync amazonKinesis() {
    log.info("Initializing output kinesis");
    return AmazonKinesisAsyncClientBuilder
        .standard()
        .withCredentials(createStsAssumeRoleCredentialsProvider())
        .build();
  }

  private AWSCredentialsProvider createStsAssumeRoleCredentialsProvider() {
    return new STSAssumeRoleSessionCredentialsProvider.Builder(rrsIamRoleArn, sessionName).build();
  }

  @Bean
  public AWSCredentialsProvider awsCredentialsProvider() {
    return new DefaultAWSCredentialsProviderChain();
  }

  @Bean
  public RegionProvider regionProvider() {
    return new StaticRegionProvider(region);
  }
}
