package com.azouz.hrrecruitingservice.repository;

import com.azouz.hrrecruitingservice.notification.LoggerNotification;
import com.azouz.hrrecruitingservice.notification.NotificationPublisher;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mniebes <mario.niebes@zalando.de>
 */
@Configuration
public class RepositoryConfiguration {

  @Bean
  public NotificationPublisher notificationPublisher() {
    return new NotificationPublisher(Lists.newArrayList(new LoggerNotification()));
  }

  @Bean
  public RecruitingServiceRepository recruitingServiceRepository(
      final NotificationPublisher notificationPublisher) {
    return new InMemoryRecruitingServiceRepository(notificationPublisher);
  }
}
