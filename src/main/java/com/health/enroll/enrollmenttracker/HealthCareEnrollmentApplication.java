package com.health.enroll.enrollmenttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class HealthCareEnrollmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(HealthCareEnrollmentApplication.class, args);
  }
}
