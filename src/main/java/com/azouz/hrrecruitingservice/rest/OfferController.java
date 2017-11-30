package com.azouz.hrrecruitingservice.rest;

/**
 * @author mazouz
 */

import com.azouz.hrrecruitingservice.domain.GetAllofferResponse;
import com.azouz.hrrecruitingservice.domain.Offer;
import com.azouz.hrrecruitingservice.repository.RecruitingServiceRepository;
import java.text.MessageFormat;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OfferController.NAMESPACE)
public class OfferController {

  public static final String NAMESPACE = "/api/offer";

  private static final Logger LOG = LoggerFactory.getLogger(OfferController.class);

  private final RecruitingServiceRepository recruitingServiceRepository;

  public OfferController(
      final RecruitingServiceRepository recruitingServiceRepository) {
    this.recruitingServiceRepository = recruitingServiceRepository;
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> createOffer(@RequestBody @Valid final Offer offer) {
    LOG.info(MessageFormat.format("Create offer: {0}", offer));
    if (recruitingServiceRepository.addOffer(offer)) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public GetAllofferResponse getOffers() {
    return new GetAllofferResponse(recruitingServiceRepository.getAllOffers());
  }

  @RequestMapping(value = "/{offer_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Offer> getOfferByName(@PathVariable("offer_name") final String offerName) {
    final Optional<Offer> offer = recruitingServiceRepository.getOfferByName(offerName);
    if (!offer.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(offer.get(), HttpStatus.OK);
  }
}
