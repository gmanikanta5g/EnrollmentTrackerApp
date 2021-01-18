package com.health.enroll.enrollmenttracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Date;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.health.enroll.enrollmenttracker.entity.Enrollee;
import com.health.enroll.enrollmenttracker.exception.BadRequestException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EnrolleeServiceTests {
  
  @Autowired
  private EnrolleeService enrolleeService;
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  
  @Test
  @Rollback(false)
  public void testCreateEnrollee() throws BadRequestException {
    Enrollee enrollee = new Enrollee();
    enrollee.setFirstName("Test");
    enrollee.setLastName("test1");
    enrollee.setBirthDate(new Date(2000, 10, 26));
    enrollee.setActivationStatus(false);
    String savedEnrolleemessage = enrolleeService.saveEnrollee(enrollee);
    int id = Integer.valueOf(savedEnrolleemessage.substring(savedEnrolleemessage.lastIndexOf(" ")+1));
    Enrollee savedEnrollee = enrolleeService.getEnrollee(id);    
    assertThat(savedEnrollee.getEnrolleeId()).isGreaterThan(0);
    assertThat(enrollee.getFirstName()).isEqualTo("Test");
    assertThat(enrollee.getLastName()).isEqualTo("test1");
  }

}
