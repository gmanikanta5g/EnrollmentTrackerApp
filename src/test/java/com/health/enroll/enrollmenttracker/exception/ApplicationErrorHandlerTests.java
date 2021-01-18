package com.health.enroll.enrollmenttracker.exception;

import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ApplicationErrorHandlerTests  {

  
  @InjectMocks
  ApplicationErrorHandler applicationErrorHandler;
  
  @Mock
  HttpServletRequest request;
  
  @Mock
  BadRequestException bad_request;
  
  @Mock
  DAOException daorequest;
  
  @Mock
  HttpServletException servletrequest;
  
  @Mock
  NoDataFoundException noDataFound;
  
  
  @Mock
  ObjectMapper objectMapper;
  
  @Test
  public void HandleBadRequestExceptionTest() throws JsonProcessingException {
      applicationErrorHandler.HandleBadRequestException(request,bad_request);
  }
  
  @Test
  public void HandleDaoExceptionTest() {
      try {
          applicationErrorHandler.HandleDaoException(request,daorequest);
      } catch (JsonProcessingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
  
  @Test
  public void HandleNoDataFoundException() {
      try {
          applicationErrorHandler.HandleNoDataFoundException(request,noDataFound);
      } catch (JsonProcessingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
  
  @Test
  public void HandleHttpServletExceptionTest() {
      try {
          applicationErrorHandler.HandleHttpServletException(request,servletrequest);
      } catch (JsonProcessingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
  
  @Test
  public void HandleSqlRequestException() throws JsonProcessingException {
      applicationErrorHandler.HandleSqlRequestException(request);
  }
}
