package com.health.enroll.enrollmenttracker.model;

import java.sql.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.health.enroll.enrollmenttracker.entity.Enrollee;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class EnrolleeTest {

  @InjectMocks
  Enrollee enrollee;

  public Enrollee getenrollee() {
    enrollee = new Enrollee();
    enrollee.setFirstName("Test");
    enrollee.setLastName("test1");
    enrollee.setBirthDate(new Date(2000, 10, 26));
    enrollee.setActivationStatus(false);
    return new Enrollee();
  }

  @Test
  public void getFirstNameTest() {
    Enrollee object = getenrollee();
    object.getFirstName();
  }

  @Test
  public void setfirstNameTest() {
    Enrollee object = getenrollee();
    object.setFirstName("stringvlaue");
  }


}
