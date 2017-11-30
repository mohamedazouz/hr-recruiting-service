package com.azouz.hrrecruitingservice.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mniebes <mario.niebes@zalando.de>
 */
@Configuration
public class RepositoryConfiguration {

  @Bean
  public RecruitingServiceRepository recruitingServiceRepository() {
    return new InMemoryRecruitingServiceRepository();
  }
}
