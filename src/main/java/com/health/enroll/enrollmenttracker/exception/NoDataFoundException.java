package com.health.enroll.enrollmenttracker.exception;

public class NoDataFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

    public NoDataFoundException() {
        super();
    }

    public NoDataFoundException(final String message) {
        super(message);
    }
}
   