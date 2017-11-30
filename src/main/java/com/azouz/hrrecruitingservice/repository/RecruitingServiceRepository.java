package com.azouz.hrrecruitingservice.repository;

import com.azouz.hrrecruitingservice.domain.Offer;
import java.util.Collection;
import java.util.Optional;

/**
 * @author mazouz
 */
public interface RecruitingServiceRepository {

  boolean addOffer(final Offer offer);

  Optional<Offer> getOfferByName(final String name);

  Collection<Offer> getAllOffers();
}
