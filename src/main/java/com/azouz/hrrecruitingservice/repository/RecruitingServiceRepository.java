package com.azouz.hrrecruitingservice.repository;

import com.azouz.hrrecruitingservice.domain.CandidateApplication;
import com.azouz.hrrecruitingservice.domain.CandidateApplicationStatus;
import com.azouz.hrrecruitingservice.domain.Offer;
import com.azouz.hrrecruitingservice.exception.DuplicateRecordException;
import com.azouz.hrrecruitingservice.exception.NotFoundException;
import java.math.BigInteger;
import java.util.Collection;

/**
 * @author mazouz
 */
public interface RecruitingServiceRepository {

  void addOffer(final Offer offer) throws DuplicateRecordException;

  Offer getOfferByName(final String name) throws NotFoundException;

  Collection<Offer> getAllOffers();

  void applyForCandidate(final CandidateApplication candidateApplication)
      throws NotFoundException, DuplicateRecordException;

  Collection<CandidateApplication> getCandidatesPerOffer(final String offerName)
      throws NotFoundException;

  void changeCandidateStatus(final String email, final String offerName,
      final CandidateApplicationStatus status)
      throws NotFoundException;

  BigInteger totalNumberOfApplications();
}
