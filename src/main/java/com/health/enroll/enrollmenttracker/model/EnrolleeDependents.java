package com.health.enroll.enrollmenttracker.model;

import java.io.Serializable;
import java.util.List;
import com.health.enroll.enrollmenttracker.entity.Dependent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 
 * @author gmani
 * This POJO acts as a Inputs request for Dependents Operations
 */
@Getter
@Setter
@ToString(of = {"enrolleeId", "dependents"})
@ApiModel(description = "Request Object for Dependent Controller")
public class EnrolleeDependents implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 2L;

  @ApiModelProperty(notes = "Enrollee Id details", required = true, example = "12")
  private int enrolleeId;
  private List<Dependent> dependents;


}
