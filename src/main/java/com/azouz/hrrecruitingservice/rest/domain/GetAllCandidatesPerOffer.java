package com.azouz.hrrecruitingservice.rest.domain;

import com.azouz.hrrecruitingservice.domain.CandidateApplication;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Collection;

/**
 * @author mazouz
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetAllCandidatesPerOffer {

  final Collection<CandidateApplication> candidateApplications;

  public GetAllCandidatesPerOffer(final Collection<CandidateApplication> candidateApplications) {
    this.candidateApplications = candidateApplications;
  }

  public Collection<CandidateApplication> applications() {
    return candidateApplications;
  }
}
