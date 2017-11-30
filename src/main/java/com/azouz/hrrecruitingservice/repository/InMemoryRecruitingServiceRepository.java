package com.azouz.hrrecruitingservice.repository;

import com.azouz.hrrecruitingservice.domain.Offer;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * @author mazouz
 */
public class InMemoryRecruitingServiceRepository implements RecruitingServiceRepository {

  final Set<Offer> offers;

  public InMemoryRecruitingServiceRepository() {
    this.offers = Sets.newHashSet();
  }

  @Override
  public boolean addOffer(final Offer offer) {
    return offers.add(offer);
  }

  @Override
  public Optional<Offer> getOfferByName(final String name) {
    return offers.stream().filter(offer -> offer.getName().equals(name)).findFirst();
  }

  @Override
  public Collection<Offer> getAllOffers() {
    return offers;
  }
}
