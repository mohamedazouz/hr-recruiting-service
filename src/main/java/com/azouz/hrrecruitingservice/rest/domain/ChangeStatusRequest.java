package com.azouz.hrrecruitingservice.rest.domain;

import com.azouz.hrrecruitingservice.domain.CandidateApplicationStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author mazouz
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChangeStatusRequest {

  @NotNull
  private String offerName;

  @NotNull
  @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
      + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
      + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
      message = "{invalid.email}")
  private String email;


  @NotNull
  private CandidateApplicationStatus status;

  public String getOfferName() {
    return offerName;
  }

  public void setOfferName(final String offerName) {
    this.offerName = offerName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public CandidateApplicationStatus getStatus() {
    return status;
  }

  public void setStatus(final CandidateApplicationStatus status) {
    this.status = status;
  }
}
