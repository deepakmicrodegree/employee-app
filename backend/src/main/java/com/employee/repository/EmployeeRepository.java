package com.employee.repository;

import com.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * EmployeeRepository - Data Access Layer
 * Provides CRUD operations for Employee entity
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    /**
     * Find employee by email
     * @param email the employee email
     * @return optional containing the employee if found
     */
    Optional<Employee> findByEmail(String email);
    
    /**
     * Find all employees in a specific department
     * @param department the department name
     * @return list of employees in the department
     */
    List<Employee> findByDepartment(String department);
}
