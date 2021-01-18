package com.health.enroll.enrollmenttracker.service;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.health.enroll.enrollmenttracker.entity.Dependent;

/**
 * 
 * @author gmani
 * Repo Interface to perform DB Operations related to the Enrollee-Dependents
 */
@Repository
public interface DependentRepository extends CrudRepository<Dependent, Integer> {     
    
    List<Dependent> findDependentsByEnrolleeId(int enrollee_id);
    
    @Query(value = "select dependentId from Dependent where enrollee_id=:enrolleeId")
    List<Integer> findDepedentIdsByEnrolleeId(@Param("enrolleeId") int enrollee_id); 
    
    void deleteByEnrolleeId(int enrollee_id);
}
