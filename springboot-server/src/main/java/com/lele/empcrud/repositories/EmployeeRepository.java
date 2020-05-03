/*
 * Create EmployeeRepository for accessing data from the database.
 * */

package com.lele.empcrud.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lele.empcrud.models.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

}
