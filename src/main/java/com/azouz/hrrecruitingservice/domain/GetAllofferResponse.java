package com.azouz.hrrecruitingservice.domain;

import java.util.Collection;

/**
 * @author mazouz
 */
public class GetAllofferResponse {

  final Collection<Offer> offers;

  public GetAllofferResponse(final Collection<Offer> offers) {
    this.offers = offers;
  }

  public Collection<Offer> getOffers() {
    return offers;
  }
}
