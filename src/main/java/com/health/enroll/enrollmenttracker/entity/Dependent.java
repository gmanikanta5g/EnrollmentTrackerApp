package com.health.enroll.enrollmenttracker.entity;


import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * 
 * Dependent Entity Object to persist the Enrollee Data into Database
 * @author gmani
 *
 */
@Getter
@Setter
@Entity
@ToString(of = {"dependentId", "firstName", "lastName", "birthDate", "enrolleeId"})
@Table( name = "dependent" )
public class Dependent implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="DEPENDENT_ID")
  private int dependentId;
  @Column(name="FIRST_NAME")
  private String firstName;
  @Column(name="LAST_NAME")
  private String lastName;
  @Column(name="BIRTH_DATE")
  private Date birthDate;
  @Column(name="ENROLLEE_ID")
  private int enrolleeId;
  
  public Dependent() {
    // TODO Auto-generated constructor stub
  }
  

}


