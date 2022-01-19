/**
 * 
 */
package com.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.models.Employee;

/**
 * @author thoma
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}