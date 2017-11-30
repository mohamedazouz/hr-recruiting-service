package com.azouz.hrrecruitingservice.exception;

import java.text.MessageFormat;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * @author mazouz
 */
@ControllerAdvice
public class ControllerAdviceTraits implements ProblemHandling {

  private static final Logger LOG = LoggerFactory.getLogger(ControllerAdviceTraits.class);

  private static String generateErrorTraceId() {
    return MessageFormat.format("ETI{0}", RandomStringUtils.randomAlphanumeric(24));
  }

  @Override
  @ExceptionHandler
  public ResponseEntity<Problem> handleThrowable(final Throwable throwable,
      final NativeWebRequest request) {
    final String errorTraceId = generateErrorTraceId();
    LOG.error("InternalServerError ({}):", errorTraceId, throwable);

    final ThrowableProblem problem = Problem.valueOf(Response.Status.INTERNAL_SERVER_ERROR,
        MessageFormat.format("An internal error happened. Please report it. ({0})", errorTraceId));
    return create(problem, request);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<Problem> handleHttpClientErrorException(
      final HttpClientErrorException hcee) {
    final int statusCode = hcee.getRawStatusCode();
    final Problem problem = Problem
        .valueOf(Response.Status.fromStatusCode(statusCode), hcee.getMessage());
    return new ResponseEntity<>(problem, HttpStatus.valueOf(statusCode));
  }

}
