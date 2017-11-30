package com.azouz.hrrecruitingservice.rest;

/**
 * @author mazouz
 */

import com.azouz.hrrecruitingservice.domain.CandidateApplication;
import com.azouz.hrrecruitingservice.domain.Offer;
import com.azouz.hrrecruitingservice.exception.DuplicateRecordException;
import com.azouz.hrrecruitingservice.exception.NotFoundException;
import com.azouz.hrrecruitingservice.repository.RecruitingServiceRepository;
import com.azouz.hrrecruitingservice.rest.domain.ChangeStatusRequest;
import com.azouz.hrrecruitingservice.rest.domain.GetAllCandidatesPerOffer;
import com.azouz.hrrecruitingservice.rest.domain.GetAllofferResponse;
import com.azouz.hrrecruitingservice.rest.domain.TotalNumberOfApplicationsResponse;
import java.text.MessageFormat;
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
  public ResponseEntity<Void> createOffer(@RequestBody @Valid final Offer offer)
      throws DuplicateRecordException {
    LOG.info(MessageFormat.format("Create offer: {0}", offer));
    recruitingServiceRepository.addOffer(offer);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public GetAllofferResponse getOffers() {
    return new GetAllofferResponse(recruitingServiceRepository.getAllOffers());
  }

  @RequestMapping(value = "/{offer_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Offer> getOfferByName(@PathVariable("offer_name") final String offerName)
      throws NotFoundException {
    final Offer offer = recruitingServiceRepository.getOfferByName(offerName);
    return new ResponseEntity<>(offer, HttpStatus.OK);
  }

  @RequestMapping(value = "/apply", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> applyForOffer(
      @RequestBody @Valid final CandidateApplication candidateApplication)
      throws NotFoundException, DuplicateRecordException {
    LOG.info(MessageFormat.format("Create application: {0}", candidateApplication));
    recruitingServiceRepository.applyForCandidate(candidateApplication);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{offer_name}/candidate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public GetAllCandidatesPerOffer getAllCandidateByOfferName(
      @PathVariable("offer_name") final String offerName) throws NotFoundException {
    return new GetAllCandidatesPerOffer(
        recruitingServiceRepository.getCandidatesPerOffer(offerName));
  }

  @RequestMapping(value = "/change_candidate_status", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> changeCandidateStatusByEmailAndOfferName(
      @RequestBody @Valid final ChangeStatusRequest changeStatusRequest) throws NotFoundException {

    recruitingServiceRepository.changeCandidateStatus(changeStatusRequest.getEmail(),
        changeStatusRequest.getOfferName(), changeStatusRequest.getStatus());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/application_number", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public TotalNumberOfApplicationsResponse totalNumberOfApplications() {

    return new TotalNumberOfApplicationsResponse(
        recruitingServiceRepository.totalNumberOfApplications());
  }
}
