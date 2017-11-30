package com.azouz.hrrecruitingservice.notification;

import com.azouz.hrrecruitingservice.domain.CandidateApplicationStatus;
import com.google.common.base.MoreObjects;

/**
 * @author mazouz
 */
public class NotificationMessage {

  final String userEmail;
  final String offerName;
  final CandidateApplicationStatus status;

  public NotificationMessage(final String userEmail, final String offerName,
      final CandidateApplicationStatus status) {
    this.userEmail = userEmail;
    this.offerName = offerName;
    this.status = status;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public String getOfferName() {
    return offerName;
  }

  public CandidateApplicationStatus getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("userEmail", userEmail)
        .add("offerName", offerName)
        .add("status", status)
        .toString();
  }
}
