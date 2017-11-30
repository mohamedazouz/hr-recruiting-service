package com.azouz.hrrecruitingservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 * @author mazouz
 */
@JsonDeserialize(builder = Offer.Builder.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Offer {

  @NotNull
  private final String name;

  @Future
  private final Date startDate;
  private final int applicationsNums;

  public Offer(final Builder builder) {
    this.name = builder.name;
    this.startDate = builder.startDate;
    this.applicationsNums = builder.applicationsNums;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(final Offer offer) {
    return new Builder(offer);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Offer)) {
      return false;
    }
    final Offer offer = (Offer) o;
    return Objects.equal(name, offer.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("startDate", startDate)
        .add("applicationsNums", applicationsNums)
        .toString();
  }

  public String getName() {
    return name;
  }

  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getStartDate() {
    return startDate;
  }

  public int getApplicationsNums() {
    return applicationsNums;
  }

  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class Builder {

    private String name;
    private Date startDate;
    private int applicationsNums;

    private Builder() {
    }

    private Builder(final Offer offer) {
      this.name = offer.name;
      this.startDate = offer.startDate;
      this.applicationsNums = offer.applicationsNums;
    }

    public Builder withName(final String name) {
      this.name = name;
      return this;
    }

    @JsonFormat(pattern = "dd-MM-yyyy")
    public Builder withStartDate(final Date startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder withApplicationsNums(final int applicationsNums) {
      this.applicationsNums = applicationsNums;
      return this;
    }

    public Offer build() {
      return new Offer(this);
    }
  }
}
