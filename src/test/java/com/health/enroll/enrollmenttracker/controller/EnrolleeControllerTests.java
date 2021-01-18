package com.health.enroll.enrollmenttracker.controller;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.health.enroll.enrollmenttracker.entity.Enrollee;
import com.health.enroll.enrollmenttracker.exception.NoDataFoundException;
import com.health.enroll.enrollmenttracker.service.DependentService;
import com.health.enroll.enrollmenttracker.service.EnrolleeService;
import com.health.enroll.enrollmenttracker.util.HealthCareUtil;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class EnrolleeControllerTests {

  @Autowired
  EnrolleeService enrolleeService;

  @Autowired
  DependentService dependentService;

  @Autowired
  HealthCareUtil healthCareUtil;
  
  @Autowired
  EnrolleeController enrolleeController;
  
  HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  
  @Test
  public void testGetEnrollee() throws NoDataFoundException  {
    Enrollee enrollee = new Enrollee();
    enrollee.setFirstName("Test");
    enrollee.setLastName("test1");
    enrollee.setBirthDate(new Date(2000, 10, 26));
    enrollee.setActivationStatus(false);
    Mockito.when(enrolleeService.getEnrollee(Mockito.anyInt())).thenReturn(enrollee);
    enrolleeController.getEnrollee(12);
  
  }
  
}
