package com.health.enroll.enrollmenttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.enroll.enrollmenttracker.entity.Enrollee;
import com.health.enroll.enrollmenttracker.exception.BadRequestException;
import com.health.enroll.enrollmenttracker.util.HealthCareUtil;

@Service
@Transactional
public class EnrolleeService {

  @Autowired
  private EnrolleeRepository enrolleeRepository;
  
  @Autowired
  private HealthCareUtil healthCareUtil;
  
  @Autowired
  private DependentRepository dependentRepository;

  
  /**
   * Following Method Save the Enrollee Data if its enrollee doesn't exist
   * @param enrollee
   * @return message with created id.
   * @throws BadRequestException
   */
  public String saveEnrollee(Enrollee enrollee) throws BadRequestException {
    boolean isValid= healthCareUtil.validateInputRequest(enrollee, "AddEnrolle");
    if(!isValid) {
      throw new BadRequestException("Invalid Input");
    }
    // Validate if record Exists (implementation can be added after solid rules and more columns added for an enrollee) and if record exists return back with details without creating new record
    
    Enrollee savedEnrollee = enrolleeRepository.save(enrollee);   
    return "Enrollee :'"+ savedEnrollee.getFirstName()+" "+savedEnrollee.getLastName() + " ' is Created with id :- "+ String.valueOf(savedEnrollee.getEnrolleeId());
    
  }
  
  
  /**
   * Following Method update the existing Enrollee Details
   * @param enrollee
   * @return bollean value after updating the value
   * @throws BadRequestException
   */

  public boolean modifyEnrollee(Enrollee enrollee) throws BadRequestException {
    boolean isValid= healthCareUtil.validateInputRequest(enrollee, "UpdateEnrolle");
    if(!isValid) {
      throw new BadRequestException("Invalid Input");
    }
    if (healthCareUtil.isEnrolleeExists(enrollee.getEnrolleeId())) {
      enrolleeRepository.save(enrollee);
      return true;
    } else {
      return false;
    }
  }
  
  
  /**
   * Following method delete the dependents first and remove the enrollee records from the DB.
   * @param enrollee
   * @return boolean value
   * @throws BadRequestException
   */

  public boolean deleteEnrollee(Enrollee enrollee) throws BadRequestException {
    boolean isValid= healthCareUtil.validateInputRequest(enrollee, "DeleteEnrolle");
    if(!isValid)
      throw new BadRequestException("Invalid Input");
    if (healthCareUtil.isEnrolleeExists(enrollee.getEnrolleeId())) {
      dependentRepository.deleteByEnrolleeId(enrollee.getEnrolleeId());
      enrolleeRepository.deleteById(enrollee.getEnrolleeId());
      return true;
    } else {
      return false;
    }
  }
  
    
  /**
   * 
   * Following Method provide the Enrollee Details for a given enrolleeid
   * @param enrolleId
   * @return
   */
  public Enrollee getEnrollee(int enrolleId){
    return enrolleeRepository.findById(enrolleId).get();
    
  }
}
