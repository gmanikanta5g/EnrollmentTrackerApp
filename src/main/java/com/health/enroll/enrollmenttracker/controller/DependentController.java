package com.health.enroll.enrollmenttracker.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.health.enroll.enrollmenttracker.entity.Dependent;
import com.health.enroll.enrollmenttracker.model.EnrolleeDependents;
import com.health.enroll.enrollmenttracker.service.DependentService;
import com.health.enroll.enrollmenttracker.service.EnrolleeService;
import com.health.enroll.enrollmenttracker.exception.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Tag;
import io.swagger.annotations.SwaggerDefinition;


@Api(value = "DependentController", tags = {"Dependents Controller"})
@SwaggerDefinition(tags = @Tag(name = "Dependents Controller",
    description = "Provides Add/Update/Modify/Delete functionality for Dependents of an Enrollee"))
@Controller
@RequestMapping("api/v1")
public class DependentController {

  @Autowired
  EnrolleeService enrolleeService;

  @Autowired
  DependentService dependentService;

  @ApiOperation(value = "Add Dependents to an Enrollee",
      notes = "** Enrollee ID and dependent Attributes are required for this operation **")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "CREATED"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @PostMapping("/dependents")
  public ResponseEntity<String> addDependents(@RequestBody EnrolleeDependents dependents) throws BadRequestException {
    String message = dependentService.saveDependents(dependents);
    return new ResponseEntity<String>(message, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Update/Modify Dependents of an Enrollee",
      notes = "** Dependent Id and associated Enrollee ID are required fields in the Request **")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "NOT_FOUND"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @PutMapping("/dependents")
  public ResponseEntity<String> updateDependents(@RequestBody EnrolleeDependents dependents) throws NoDataFoundException, BadRequestException  {
    if (!dependentService.updateDependents(dependents))
      throw new NoDataFoundException("Dependents not existed in the DB");    
    else
     return new ResponseEntity<String>("Update Enrollee Details", HttpStatus.OK); 
  }

  @ApiOperation(value = "Delete Dependents of an Enrollee",
      notes = "** Dependent Id and associated Enrollee ID are required fields in the Request **")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "NOT_FOUND"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @DeleteMapping("/dependents")
  public ResponseEntity<String> deleteDependents(@RequestBody EnrolleeDependents dependents) throws BadRequestException {
    if (dependentService.deleteDependents(dependents))
      return new ResponseEntity<String>("Deleted Dependents of an Enrollee", HttpStatus.OK);
    else
      return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
  }

  @ApiOperation(value = "Get All Dependents of an Enrollee",
      notes = "** Enrollee ID is the required field for this operation **")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "NOT_FOUND"),
      @ApiResponse(code = 500, message = "Internal Server Error")})
  @GetMapping(value = "/dependents/enrollee/{enrolleeId}",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<Dependent>> getEnrolleeDependents(@PathVariable Integer enrolleeId) {
    List<Dependent> dependents = dependentService.getDependents(enrolleeId.intValue());
    if (dependents.size() > 0)
      return new ResponseEntity<List<Dependent>>(dependents, HttpStatus.OK);
    else
      return new ResponseEntity<List<Dependent>>(HttpStatus.NOT_FOUND);
  }
}


