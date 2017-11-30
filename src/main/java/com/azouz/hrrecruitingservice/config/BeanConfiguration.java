package com.azouz.hrrecruitingservice.config;

import com.azouz.hrrecruitingservice.repository.RecruitingServiceRepository;
import com.azouz.hrrecruitingservice.repository.RepositoryConfiguration;
import com.azouz.hrrecruitingservice.rest.OfferController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author mazouz
 */
@Configuration
@Import({RepositoryConfiguration.class, RestConfiguration.class})
public class BeanConfiguration {


  @Bean
  public OfferController offerController(
      final RecruitingServiceRepository recruitingServiceRepository) {
    return new OfferController(recruitingServiceRepository);
  }

}
