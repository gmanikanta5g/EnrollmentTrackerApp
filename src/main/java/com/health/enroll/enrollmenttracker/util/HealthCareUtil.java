package com.health.enroll.enrollmenttracker.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import com.health.enroll.enrollmenttracker.entity.Dependent;
import com.health.enroll.enrollmenttracker.entity.Enrollee;
import com.health.enroll.enrollmenttracker.model.EnrolleeDependents;
import com.health.enroll.enrollmenttracker.service.EnrolleeRepository;

/**
 * 
 * @author gmani Utility Class to perform Validation and transformations functionality
 */

@Service
public class HealthCareUtil {

  @Autowired
  EnrolleeRepository enrolleeRepository;

  /**
   * Following Method Transforms the EnrolleeDependents into List of Dependents Object
   * 
   * @param enrolleeDependents
   * @return List<Dependents> after successful Transformation
   */
  public List<Dependent> tranformEnrolleeObject(EnrolleeDependents enrolleeDependents) {
    List<Dependent> dependents = new ArrayList<Dependent>();
    int enrolleeId = enrolleeDependents.getEnrolleeId();
    for (Dependent dependentAttr : enrolleeDependents.getDependents()) {
      Dependent dependent = new Dependent();
      dependent.setEnrolleeId(enrolleeId);
      if (dependentAttr.getFirstName() != null)
        dependent.setFirstName(dependentAttr.getFirstName());
      if (dependentAttr.getLastName() != null)
        dependent.setLastName(dependentAttr.getLastName());
      if (dependentAttr.getBirthDate() != null)
        dependent.setBirthDate(dependentAttr.getBirthDate());
      if (dependentAttr.getDependentId() > 0)
        dependent.setDependentId(dependentAttr.getDependentId());
      dependents.add(dependent);
    }
    return dependents;
  }


  /**
   * Following Utility Method is used for Checking whether Enrollee exists or not
   * 
   * @param enrolleeId
   * @return boolean value after checking in the Database.
   */
  public boolean isEnrolleeExists(int enrolleeId) {
    return enrolleeRepository.findById(enrolleeId).isPresent();
  }


  /**
   * Following Method checks if the given object has any dependents or not
   * 
   * @param enrolleeDependentsRequest
   * @return boolean value after checking the collection
   */
  public boolean validateDeleteDependentsSceanrio(EnrolleeDependents enrolleeDependentsRequest) {
    if (enrolleeDependentsRequest.getDependents() != null
        && enrolleeDependentsRequest.getDependents().size() > 0)
      return false;
    else
      return true;
  }

  /**
   * Following Method Validate input values for its null/empty checks
   * 
   * @param enrollee
   * @param operation
   * @return boolean value.
   * @throws HttpMessageNotReadableException
   */

  public boolean validateInputRequest(Enrollee enrollee, String operation)
      throws HttpMessageNotReadableException {
    boolean flag = false;
    switch (operation) {
      case "AddEnrolle":
        if (enrollee.getFirstName() != null && enrollee.getFirstName() != ""
            && enrollee.getLastName() != null && enrollee.getLastName() != ""
            && enrollee.getBirthDate() != null) {
          flag = true;
        }
        break;
      case "UpdateEnrolle":
        if (enrollee.getFirstName() != null && enrollee.getFirstName() != ""
            && enrollee.getEnrolleeId() > 0 && enrollee.getLastName() != null
            && enrollee.getLastName() != "" && enrollee.getBirthDate() != null) {
          flag = true;
        }
        break;
      case "DeleteEnrolle":
        if (enrollee.getEnrolleeId() > 0)
          flag = true;
        break;
    }
    return flag;
  }
  
  /**
   * Following Method Validate input values for its null/empty checks
   * 
   * @param enrollee
   * @param operation
   * @return boolean value.
   * @throws HttpMessageNotReadableException
   */
  public boolean validateInputRequest(EnrolleeDependents enrolleeDependents, String operation)
      throws HttpMessageNotReadableException {
    boolean flag = false;
    switch (operation) {
      case "AddDependents":
        if (enrolleeDependents.getEnrolleeId() > 0 && enrolleeDependents.getDependents() !=null
            && enrolleeDependents.getDependents().size() > 0) {
          for (Dependent d : enrolleeDependents.getDependents()) {
            if (d.getFirstName() != null && d.getFirstName() != "" && d.getLastName() != null
                && d.getLastName() != "") {
              flag = true;
            } else
              return false;
          }

          flag = true;
          break;
        }
      case "UpdateDependents":
        if (enrolleeDependents.getEnrolleeId() > 0 && enrolleeDependents.getDependents() !=null
            && enrolleeDependents.getDependents().size() > 0) {
          for (Dependent d : enrolleeDependents.getDependents()) {
            if (d.getFirstName() != null && d.getFirstName() != "" && d.getLastName() != null
                && d.getLastName() != "" && d.getDependentId() > 0) {
              flag = true;
            } else
              return false;
          }
          flag = true;
          break;
        }

      case "DeleteDependents":
        if (enrolleeDependents.getEnrolleeId() > 0 && enrolleeDependents.getDependents() !=null
            && enrolleeDependents.getDependents().size() > 0) {
          for (Dependent d : enrolleeDependents.getDependents()) {
            if (d.getDependentId() > 0) {
              flag = true;
            } else
              return false;
          }
          flag = true;
        }
        break;

    }
    return flag;
  }
}
