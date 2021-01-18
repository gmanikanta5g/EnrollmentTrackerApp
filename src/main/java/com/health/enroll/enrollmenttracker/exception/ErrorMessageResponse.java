package com.health.enroll.enrollmenttracker.exception;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * Standard Error Message POJO for all Exceptions
 * @author gmani
 *
 */

@Getter
@Setter
public class ErrorMessageResponse {
  public ErrorMessageResponse(int value, Date date, String message) {
    this.statusCode = value;
    this.timestamp = date;
    this.message = message;
  }

  private int statusCode;
  private Date timestamp;
  private String message;
}
