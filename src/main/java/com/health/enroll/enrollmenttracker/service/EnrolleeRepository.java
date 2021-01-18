package com.health.enroll.enrollmenttracker.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.health.enroll.enrollmenttracker.entity.Enrollee;

/**
 * 
 * @author gmani
 * Repo Interface to perform DB Operations related to the Enrollee
 */
@Repository
public interface EnrolleeRepository extends CrudRepository<Enrollee, Integer>{

}
