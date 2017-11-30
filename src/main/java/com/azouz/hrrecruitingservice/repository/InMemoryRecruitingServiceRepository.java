package com.azouz.hrrecruitingservice.repository;

import com.azouz.hrrecruitingservice.domain.CandidateApplication;
import com.azouz.hrrecruitingservice.domain.CandidateApplicationStatus;
import com.azouz.hrrecruitingservice.domain.Offer;
import com.azouz.hrrecruitingservice.domain.Offer.Builder;
import com.azouz.hrrecruitingservice.exception.DuplicateRecordException;
import com.azouz.hrrecruitingservice.exception.NotFoundException;
import com.azouz.hrrecruitingservice.notification.NotificationMessage;
import com.azouz.hrrecruitingservice.notification.NotificationPublisher;
import com.google.common.collect.Sets;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mazouz
 */
public class InMemoryRecruitingServiceRepository implements RecruitingServiceRepository {

  final Set<Offer> offers;

  final Set<CandidateApplication> candidateApplications;
  final NotificationPublisher notificationPublisher;

  public InMemoryRecruitingServiceRepository(
      final NotificationPublisher notificationPublisher) {
    this.offers = Sets.newHashSet();
    this.candidateApplications = Sets.newHashSet();
    this.notificationPublisher = notificationPublisher;
  }

  @Override
  public void addOffer(final Offer offer) throws DuplicateRecordException {
    final boolean added = offers.add(offer);
    if (!added) {
      throw new DuplicateRecordException(
          MessageFormat.format("Offer is already exists", offer.getName()));
    }
  }

  @Override
  public Offer getOfferByName(final String name) throws NotFoundException {
    final Optional<Offer> offer = offers.stream().filter(off -> off.getName().equals(name))
        .findFirst();
    if (offer.isPresent()) {
      return offer.get();
    }
    throw new NotFoundException(
        MessageFormat.format("No offer with name: {0}", name));
  }

  @Override
  public Collection<Offer> getAllOffers() {
    return offers;
  }

  @Override
  public void applyForCandidate(final CandidateApplication candidateApplication)
      throws NotFoundException, DuplicateRecordException {
    final Offer offer = getOfferByName(candidateApplication.getOfferName());
    if (candidateApplications.contains(candidateApplication)) {
      throw new DuplicateRecordException(
          MessageFormat.format("Application with email: {0} is already applied for offer: {1}",
              candidateApplication.getEmail(), candidateApplication.getOfferName()));
    }

    changeCandidateStatus(candidateApplication, CandidateApplicationStatus.APPLIED);
    updateOfferNumOfApplication(offer);
  }

  private void changeCandidateStatus(final CandidateApplication candidateApplication,
      final CandidateApplicationStatus status) {
    final CandidateApplication.Builder builder = CandidateApplication.builder(candidateApplication);
    builder.withStatus(status);
    candidateApplications.remove(candidateApplication);
    candidateApplications.add(builder.build());
  }

  private void updateOfferNumOfApplication(final Offer offer) {
    final Builder builder = Offer.builder(offer);
    builder.withApplicationsNums(offer.getApplicationsNums() + 1);
    offers.remove(offer);
    offers.add(builder.build());
  }

  @Override
  public Collection<CandidateApplication> getCandidatesPerOffer(final String offerName)
      throws NotFoundException {
    if (!isOfferExistByName(offerName)) {
      throw new NotFoundException(
          MessageFormat.format("No offer with name: {0}", offerName));
    }
    return candidateApplications.stream().filter(app -> app.getOfferName().equals(offerName))
        .collect(Collectors.toSet());
  }

  @Override
  public void changeCandidateStatus(final String email, final String offerName,
      final CandidateApplicationStatus status)
      throws NotFoundException {
    final Optional<CandidateApplication> candidateApplication = getCandidateByEmailAndOfferName(
        email, offerName);
    if (!candidateApplication.isPresent()) {
      throw new NotFoundException(
          MessageFormat.format("No application with email: {0} and offer: {1}",
              email, email));
    }
    changeCandidateStatus(candidateApplication.get(), status);
    notificationPublisher
        .publishCandidateStatusChange(new NotificationMessage(email, offerName, status));
  }


  private boolean isOfferExistByName(final String offerName) {
    return offers.contains(Offer.builder().withName(offerName).build());
  }

  private Optional<CandidateApplication> getCandidateByEmailAndOfferName(final String email,
      final String offerName) {
    return candidateApplications.stream()
        .filter(app -> app.getEmail().equals(email) && app.getOfferName().equals(offerName))
        .findFirst();
  }

  @Override
  public BigInteger totalNumberOfApplications() {
    return BigInteger.valueOf(candidateApplications.size());
  }


}
