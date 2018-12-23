# Description

This project configures 2 Kinesis streams: one as input and another one as output. It uses `spring-cloud-stream-binder-kinesis 1.0.0.RELEASE`.

It is meant to reproduce the issues described [here](https://github.com/spring-cloud/spring-cloud-stream/issues/1569#issue-392818051)

In able to use it, you need:
* Java8, Maven.
* To have your AWS credentials in a place where `DefaultAWSCredentialsProviderChain` can find them.
* Add the configuration for the region, kinesis streams (the output stream should not be accessible with your permissions, but with a role you can assume), consumer group in `application.yml`.

In the current state, the Kinesis client configured in `KinesisOutputConfiguration` is never initialized and the application will fail to start because it cannot find a Credentials provider for the output binder.
* When you uncomment the `spring-cloud-commons` dependency from the `pom.xml`, you will see that the Kinesis client configured in `KinesisOutputConfiguration` is being invoked and the previous error disappears.
* When you uncomment the `spring-cloud-context` dependency from the `pom.xml`, you will see that the Kinesis client configured in `KinesisOutputConfiguration` is initialized twice.
* When you uncomment `spring.cloud.stream.myBinder.environment.spring.cloud.bootstrap.enabled` from the application.yml, you will see that the Kinesis client configured in `KinesisOutputConfiguration` is initialized once again.
* When you uncomment the `@Profile` annotation from `KinesisOutputConfiguration`, then uncomment also `spring.cloud.stream.myBinder.environment.spring.main.additionalProfiles`, then comment `spring.cloud.stream.myBinder.environment.spring.main.sources` from the application.yml, you will see that the Kinesis client configured in `KinesisOutputConfiguration` is never initialized again. 
* Finally, if `spring.jmx.default-domain` is not defined, `org.springframework.cloud.stream.binder.DefaultBinderFactory.getBinderInstance` will concatenate `null` when generating the argument that specifies the default JMX domain. The result is something like: `--spring.jmx.default-domain=nullbinder.myBinder`. Is this expected?
  * In the version of `spring-cloud-stream` picked by `spring-cloud-stream-binder-kinesis 1.0.0.RELEASE`, `spring.jmx.default-domain` has to end with `.` but I saw that it was already fixed [here](https://github.com/spring-cloud/spring-cloud-stream/issues/1408).
