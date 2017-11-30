package com.azouz.hrrecruitingservice.exception;

/**
 * @author mazouz
 */
public class NotFoundException extends Exception {

  private static final long serialVersionUID = -3932580886051016069L;

  public NotFoundException() {
    super();
  }

  public NotFoundException(final String message) {
    super(message);
  }

  public NotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(final Throwable cause) {
    super(cause);
  }

}
