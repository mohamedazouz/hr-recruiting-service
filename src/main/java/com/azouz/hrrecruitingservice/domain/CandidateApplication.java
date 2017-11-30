package com.azouz.hrrecruitingservice.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author mazouz
 */
@JsonDeserialize(builder = CandidateApplication.Builder.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CandidateApplication {

  @NotNull
  private final String offerName;

  @NotNull
  @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
      + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
      + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
      message = "{invalid.email}")
  private final String email;

  private final String resume;
  private final CandidateApplicationStatus status;

  public CandidateApplication(final Builder builder) {
    this.offerName = builder.offerName;
    this.email = builder.email;
    this.resume = builder.resume;
    this.status = builder.status;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(final CandidateApplication candidateApplication) {
    return new Builder(candidateApplication);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CandidateApplication)) {
      return false;
    }
    final CandidateApplication that = (CandidateApplication) o;
    return Objects.equal(offerName, that.offerName) &&
        Objects.equal(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(offerName, email);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("offerName", offerName)
        .add("email", email)
        .add("resume", resume)
        .add("status", status)
        .toString();
  }

  public String getOfferName() {
    return offerName;
  }

  public String getEmail() {
    return email;
  }

  public String getResume() {
    return resume;
  }

  public CandidateApplicationStatus getStatus() {
    return status;
  }

  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class Builder {

    private String offerName;
    private String email;
    private String resume;
    private CandidateApplicationStatus status;

    private Builder() {
    }

    private Builder(
        final CandidateApplication candidateApplication) {
      this.offerName = candidateApplication.offerName;
      this.email = candidateApplication.email;
      this.resume = candidateApplication.resume;
      this.status = candidateApplication.status;
    }

    public Builder withOfferName(final String offerName) {
      this.offerName = offerName;
      return this;
    }

    public Builder withEmail(final String email) {
      this.email = email;
      return this;
    }

    public Builder withResume(final String resume) {
      this.resume = resume;
      return this;
    }

    public Builder withStatus(final CandidateApplicationStatus status) {
      this.status = status;
      return this;
    }

    public CandidateApplication build() {
      return new CandidateApplication(this);
    }
  }
}
