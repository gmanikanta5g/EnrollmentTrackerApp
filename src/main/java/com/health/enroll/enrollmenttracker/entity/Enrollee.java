package com.health.enroll.enrollmenttracker.entity;


import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * 
 * Enrollee Entity Object to persist the Enrollee Data into Database
 * @author gmani
 *
 */

@Getter
@Setter
@Entity
@ToString(of = {"enrolleeId", "firstName","lastName", "activationStatus", "birthDate","phoneNumber"})
@Table( name = "enrollee" )
public class Enrollee implements Serializable  {

  /**
   * This serialVersionUID field is necessary for Serialization & Deserialization to provide version control used by compiler, 
   * makes sure Entity Object works even if we change the structure.    
   */
  private static final long serialVersionUID = 2L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ENROLLEE_ID")
  private int enrolleeId;
  @ApiModelProperty(notes = "Enrollee First Name", required = true, example = "Name")
  @Column(name="FIRST_NAME")
  private String firstName;
  @ApiModelProperty(notes = "Enrollee Last Name", required = true, example = "Name")
  @Column(name="LAST_NAME")
  private String lastName;
  @ApiModelProperty(notes = "Enrollee activation status", required = true, example = "true/false")
  @Column(name="ACTIVATION_STATUS",columnDefinition = "boolean default false")
  private boolean activationStatus;
  @ApiModelProperty(notes = "Enrollee Id details", required = true, example = "1995-12-29")
  @Column(name="BIRTH_DATE")
  private Date birthDate;
  @ApiModelProperty(notes = "Enrollee Id details", required = false, example = "+1-123-456-1234")
  @Column(name="PHONE_NUMBER")
  private String phoneNumber;
  
  
  public Enrollee()  {
    // TODO Auto-generated constructor stub
  }

  public boolean isActivationStatus() {
    return activationStatus;
  }

  public void setActivationStatus(boolean activationStatus) {
    this.activationStatus = activationStatus;
  }
  
  public boolean getActivationStatus() {
    return activationStatus;
  }
 
}
