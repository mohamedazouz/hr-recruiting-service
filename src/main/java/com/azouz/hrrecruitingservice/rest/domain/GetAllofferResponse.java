package com.azouz.hrrecruitingservice.rest.domain;

import com.azouz.hrrecruitingservice.domain.Offer;
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
