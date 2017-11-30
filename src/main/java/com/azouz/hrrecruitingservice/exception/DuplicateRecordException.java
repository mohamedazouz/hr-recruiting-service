package com.azouz.hrrecruitingservice.exception;

/**
 * @author mazouz
 */
public class DuplicateRecordException extends Exception {

  private static final long serialVersionUID = -3932580886051016069L;

  public DuplicateRecordException() {
    super();
  }

  public DuplicateRecordException(final String message) {
    super(message);
  }

  public DuplicateRecordException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public DuplicateRecordException(final Throwable cause) {
    super(cause);
  }

}