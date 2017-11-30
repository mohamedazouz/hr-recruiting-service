package com.azouz.hrrecruitingservice.config;

import com.azouz.hrrecruitingservice.exception.ControllerAdviceTraits;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;

/**
 * @author mniebes <mario.niebes@zalando.de>
 */
@Configuration
public class RestConfiguration {

  @Bean
  public Module jodaModule() {
    return new JodaModule();
  }

  @Bean
  public Module problemModule() {
    return new ProblemModule();
  }


  @Bean
  public ControllerAdviceTraits controllerAdviceTraits() {
    return new ControllerAdviceTraits();
  }

}
