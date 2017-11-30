package com.azouz.hrrecruitingservice.exception.trait;

import com.azouz.hrrecruitingservice.exception.DuplicateRecordException;
import com.azouz.hrrecruitingservice.exception.NotFoundException;
import java.text.MessageFormat;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

  @ExceptionHandler(DuplicateRecordException.class)
  public ResponseEntity<Problem> handleDuplicateRecordException(
      final DuplicateRecordException dre) {
    final Problem problem = Problem
        .valueOf(Response.Status.fromStatusCode(HttpStatus.CONFLICT.value()), dre.getMessage());
    return new ResponseEntity<>(problem, HttpStatus.CONFLICT);
  }


  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<Problem> handleNotFoundExceptionn(
      final NotFoundException exception, final NativeWebRequest request) {
    return create(Status.NOT_FOUND, exception, request);
  }

}
