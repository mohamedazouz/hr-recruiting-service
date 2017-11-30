package com.azouz.hrrecruitingservice.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author mazouz
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public enum CandidateApplicationStatus {
  APPLIED, INVITED, REJECTED, HIRED
}
