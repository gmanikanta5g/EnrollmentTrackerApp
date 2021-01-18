package com.health.enroll.enrollmenttracker.exception;

public class HttpMessageNotReadableException extends Exception{
  private static final long serialVersionUID = 1L;

  public HttpMessageNotReadableException() {
      super();
  }

  public HttpMessageNotReadableException(final String message) {
      super(message);
  }
}
