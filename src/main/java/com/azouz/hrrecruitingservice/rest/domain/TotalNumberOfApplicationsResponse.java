package com.azouz.hrrecruitingservice.rest.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigInteger;

/**
 * @author mazouz
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TotalNumberOfApplicationsResponse {

  private final BigInteger numberOfApplications;

  public TotalNumberOfApplicationsResponse(final BigInteger numberOfApplications) {
    this.numberOfApplications = numberOfApplications;
  }

  public BigInteger getNumberOfApplications() {
    return numberOfApplications;
  }

}
