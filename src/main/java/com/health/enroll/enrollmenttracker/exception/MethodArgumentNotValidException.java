package com.health.enroll.enrollmenttracker.exception;

public class MethodArgumentNotValidException extends Exception{
  private static final long serialVersionUID = 1L;

  public MethodArgumentNotValidException() {
      super();
  }

  public MethodArgumentNotValidException(final String message) {
      super(message);
  }
}
