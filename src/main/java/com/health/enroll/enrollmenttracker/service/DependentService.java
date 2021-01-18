package com.health.enroll.enrollmenttracker.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.enroll.enrollmenttracker.entity.Dependent;
import com.health.enroll.enrollmenttracker.exception.BadRequestException;
import com.health.enroll.enrollmenttracker.exception.NoDataFoundException;
import com.health.enroll.enrollmenttracker.model.EnrolleeDependents;
import com.health.enroll.enrollmenttracker.util.HealthCareUtil;

/**
 * 
 * @author gmani Service Class to perform all the Dependent related operations.
 */
@Service
@Transactional
public class DependentService {

  @Autowired
  DependentRepository dependentRepository;

  @Autowired
  EnrolleeRepository enrolleeRepository;

  @Autowired
  HealthCareUtil healthCareUtil;

  private static final Logger logger = LoggerFactory.getLogger(DependentService.class);

  /**
   * Following Method takes the input object and Write the Dependents into the Database after
   * preliminary Validation.
   * 
   * @param enrolleeDependentsRequest
   * @return String Value - providing the Object ids created in this process
   * @throws BadRequestException 
   * 
   */
  public String saveDependents(EnrolleeDependents enrolleeDependentsRequest) throws BadRequestException {
    boolean isValid= healthCareUtil.validateInputRequest(enrolleeDependentsRequest, "AddDependents");
    if (!isValid) {
      throw new BadRequestException("Invalid Input");
    }
    List<Dependent> dependents = healthCareUtil.tranformEnrolleeObject(enrolleeDependentsRequest);    
    dependents = (List<Dependent>) dependentRepository.saveAll(dependents);
    return "Added " + dependents.size() + " Dependents to the Enrollee with Id "
        + enrolleeDependentsRequest.getEnrolleeId();

  }


  /**
   * Following Method Modify the dependents data of an enrollee as per given Input Object after
   * preliminary Validation.
   * 
   * @param enrolleeDependentsRequest
   * @return boolean Value after performing the update Operations
   * @throws NoDataFoundException
   * @throws BadRequestException 
   * 
   */
  public boolean updateDependents(EnrolleeDependents enrolleeDependentsRequest)
      throws NoDataFoundException, BadRequestException {
    boolean isValid= healthCareUtil.validateInputRequest(enrolleeDependentsRequest, "UpdateDependents");
    if(!isValid) {
      throw new BadRequestException("Invalid Input");
    }
    if (healthCareUtil.isEnrolleeExists(enrolleeDependentsRequest.getEnrolleeId())) {
      List<Dependent> dependents = healthCareUtil.tranformEnrolleeObject(enrolleeDependentsRequest);
      List<Integer> dependentsIds = dependentRepository
          .findDepedentIdsByEnrolleeId(enrolleeDependentsRequest.getEnrolleeId());

      for (Dependent d : dependents) {
        if (!dependentsIds.contains(d.getDependentId())) {
          throw new NoDataFoundException("Depedent " + d.getDependentId() + " not associated to "
              + enrolleeDependentsRequest.getEnrolleeId());
        }
        dependents = (List<Dependent>) dependentRepository.saveAll(dependents);

      }
      return true;
    } else
      return false;
  }


  /**
   * Following Method Delete the dependents of an enrollee as per the input object after preliminary
   * Validation
   * 
   * @param enrolleeDependentsRequest
   * @return boolean Value after performing the Delete Operations
   * @throws BadRequestException 
   */

  public boolean deleteDependents(EnrolleeDependents enrolleeDependentsRequest) throws BadRequestException {
    boolean isValid= healthCareUtil.validateInputRequest(enrolleeDependentsRequest, "DeleteDependents");
    if(!isValid) {
      throw new BadRequestException("Invalid Input");
    }
    if (healthCareUtil.isEnrolleeExists(enrolleeDependentsRequest.getEnrolleeId())) {      
     // boolean deleteAll =
       //   healthCareUtil.validateDeleteDependentsSceanrio(enrolleeDependentsRequest);
      int enrolleeId = enrolleeDependentsRequest.getEnrolleeId();
      //if (deleteAll) {
      //  dependentRepository.deleteByEnrolleeId(enrolleeId);
      //  logger.info("All Depedents are removed from Enrollee:" + enrolleeId);
      //} else {
        List<Integer> dependentsIds = dependentRepository
            .findDepedentIdsByEnrolleeId(enrolleeDependentsRequest.getEnrolleeId());
        for (Dependent d : enrolleeDependentsRequest.getDependents()) {
          if (dependentsIds.contains(d.getDependentId())) {
            logger.info(
                "Deleting Depedent id: " + d.getDependentId() + " of Enrollee Id " + enrolleeId);
            dependentRepository.delete(d);
            logger.info("Above Depedent is successfully deleted");
          }
      //  }
      }
      return true;
    } else
      return false;
  }


  /**
   * Following Method Return all the Dependents of a Given Enrollee Id
   * 
   * @param enrolleeId
   * @return List<dependents> after validating given the Enrollee Id.
   */

  public List<Dependent> getDependents(int enrolleeId) {
    List<Dependent> enrolleeDependents = new ArrayList<Dependent>();

    if (healthCareUtil.isEnrolleeExists(enrolleeId)) {
      enrolleeDependents = dependentRepository.findDependentsByEnrolleeId(enrolleeId);
    }

    return enrolleeDependents;
  }

}
