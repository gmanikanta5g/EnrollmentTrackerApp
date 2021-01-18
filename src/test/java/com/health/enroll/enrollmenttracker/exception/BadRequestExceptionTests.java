package com.health.enroll.enrollmenttracker.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BadRequestExceptionTests {

	@InjectMocks
	BadRequestException badRequestException;
	
	@Test
	public void test() {
		new BadRequestException();
		new BadRequestException("Error");
	}
}
