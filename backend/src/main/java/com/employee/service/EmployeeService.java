package com.employee.service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * EmployeeService - Business Logic Layer
 * Handles all employee-related business logic and operations
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Create a new employee
     * @param employee the employee object to create
     * @return the created employee
     */
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Get all employees
     * @return list of all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Get employee by ID
     * @param id the employee ID
     * @return optional containing the employee if found
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Update an existing employee
     * @param id the employee ID
     * @param employeeDetails the updated employee details
     * @return the updated employee or null if not found
     */
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            if (employeeDetails.getName() != null) {
                employee.setName(employeeDetails.getName());
            }
            if (employeeDetails.getEmail() != null) {
                employee.setEmail(employeeDetails.getEmail());
            }
            if (employeeDetails.getDepartment() != null) {
                employee.setDepartment(employeeDetails.getDepartment());
            }
            return employeeRepository.save(employee);
        }
        return null;
    }

    /**
     * Delete an employee by ID
     * @param id the employee ID
     * @return true if employee was deleted, false if not found
     */
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
