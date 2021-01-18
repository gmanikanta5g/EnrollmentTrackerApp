package com.health.enroll.enrollmenttracker.exception;

import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestControllerAdvice
@ComponentScan(basePackages = "com.health.enroll.enrollmenttracker.*")
public class ApplicationErrorHandler {
  

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorMessageResponse> HandleBadRequestException(HttpServletRequest request,
                                   BadRequestException bad_request){
    ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(  HttpStatus.BAD_REQUEST.value(), new Date(),
        bad_request.getMessage());
    return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.BAD_REQUEST);
      
  }
  
  @ExceptionHandler(SQLException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorMessageResponse>  HandleSqlRequestException(HttpServletRequest request) {
  
    ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(  HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
        "Bad SQL Exception");
    return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(DAOException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorMessageResponse> HandleDaoException(HttpServletRequest request, DAOException daorequest) throws JsonProcessingException {
      
    ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(  HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
        daorequest.getMessage());
    return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.INTERNAL_SERVER_ERROR);
      
  }
  
  @ExceptionHandler(HttpServletException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorMessageResponse>  HandleHttpServletException(HttpServletRequest request, HttpServletException servletrequest) throws JsonProcessingException {
      
    ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(  HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
        servletrequest.getMessage());
    return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.INTERNAL_SERVER_ERROR);
     
  }
  
  @ExceptionHandler(NoDataFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorMessageResponse> HandleNoDataFoundException(HttpServletRequest request,
                                               NoDataFoundException no_data) throws JsonProcessingException {
    ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(  HttpStatus.NOT_FOUND.value(), new Date(),
        no_data.getMessage());
    return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.NOT_FOUND);
     
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorMessageResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {      
    ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(  HttpStatus.BAD_REQUEST.value(), new Date(),
        e.getMessage());
    return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.BAD_REQUEST);    
  }
  
  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorMessageResponse> handleHttpMessageNotReadableException(MethodArgumentNotValidException e) {
    ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(  HttpStatus.BAD_REQUEST.value(), new Date(),
        e.getMessage());
    return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.BAD_REQUEST); 
  }

  

}
