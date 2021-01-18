package com.health.enroll.enrollmenttracker.controller;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.health.enroll.enrollmenttracker.entity.Enrollee;
import com.health.enroll.enrollmenttracker.exception.BadRequestException;
import com.health.enroll.enrollmenttracker.exception.NoDataFoundException;
import com.health.enroll.enrollmenttracker.service.DependentService;
import com.health.enroll.enrollmenttracker.service.EnrolleeService;
import com.health.enroll.enrollmenttracker.util.HealthCareUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(value = "EnrolleeController", tags = {"Enrollee Controller"})
@SwaggerDefinition(tags = @Tag(name = "Enrollee Controller",
    description = "Provides Add/Update/Modify/Delete functionality for an Enrollee "))
@Controller
@RequestMapping("api/v1")
public class EnrolleeController {

  @Autowired
  EnrolleeService enrolleeService;

  @Autowired
  DependentService dependentService;

  @Autowired
  HealthCareUtil healthCareUtil;

  @ApiOperation(value = "Register an Enrollee",
      notes = "** All Enrollee dependent Attributes (except phone Number, ID) are required for this operation **")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "CREATED"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @PostMapping("/enrollee")
  public ResponseEntity<String> addEnrollee(@RequestBody Enrollee enrollee)
      throws BadRequestException, HttpMessageNotReadableException {
    String message = enrolleeService.saveEnrollee(enrollee);
    return new ResponseEntity<String>(message, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Update/Modify an Enrollee",
      notes = "** Enrollee ID is the required field along with other fields which needs updates for this operation **")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "NOT_FOUND"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @PutMapping("/enrollee")
  public ResponseEntity<String> updateEnrollee(@RequestBody Enrollee enrollee)
      throws BadRequestException, NoDataFoundException {
    if (!enrolleeService.modifyEnrollee(enrollee)) {
      throw new NoDataFoundException("Enrolle " + enrollee.getEnrolleeId() + " didn't Exist");
    } else
      return new ResponseEntity<String>("UPDATE Completed", HttpStatus.OK);
  }

  @ApiOperation(value = "Delete an Enrollee",
      notes = "** Enrollee ID is the required field, which delete and its dependents and enrollee data for this operation **")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "NOT_FOUND"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @DeleteMapping("/enrollee")
  public ResponseEntity<String> deleteEnrollee(@RequestBody Enrollee enrollee)
      throws BadRequestException, NoDataFoundException {
    if (!enrolleeService.deleteEnrollee(enrollee)) {
      throw new NoDataFoundException("Enrolle " + enrollee.getEnrolleeId() + " didn't Exist");
    } else {
      return new ResponseEntity<String>("Deletion of enrolle completed successfully", HttpStatus.OK);
    }
  }

  @ApiOperation(value = "Get the details of an Enrollee",
      notes = "** Enrollee ID is the required field for this operation **")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "NOT_FOUND"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @GetMapping(value = "/enrollee/{enrolleeId}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Enrollee> getEnrollee(@PathVariable Integer enrolleeId) throws NoDataFoundException  {
    try {
      Enrollee enrollees = enrolleeService.getEnrollee(enrolleeId.intValue());
      return new ResponseEntity<Enrollee>(enrollees, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      throw new NoDataFoundException("Requested Enrollee not exists");
    }
  }
}
