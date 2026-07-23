package com.employee.repository;

import com.employee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Employee Repository Tests")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee(null, "John Doe", "john@example.com", "IT");
        employee2 = new Employee(null, "Jane Smith", "jane@example.com", "HR");
    }

    @Test
    @DisplayName("Test Save Employee")
    void testSaveEmployee() {
        Employee savedEmployee = employeeRepository.save(employee1);

        assertNotNull(savedEmployee.getId());
        assertEquals("John Doe", savedEmployee.getName());
        assertEquals("john@example.com", savedEmployee.getEmail());
        assertEquals("IT", savedEmployee.getDepartment());
    }

    @Test
    @DisplayName("Test Find All Employees")
    void testFindAllEmployees() {
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        List<Employee> employees = employeeRepository.findAll();

        assertNotNull(employees);
        assertEquals(2, employees.size());
    }

    @Test
    @DisplayName("Test Find Employee by ID")
    void testFindEmployeeById() {
        Employee savedEmployee = employeeRepository.save(employee1);
        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        assertTrue(foundEmployee.isPresent());
        assertEquals("John Doe", foundEmployee.get().getName());
    }

    @Test
    @DisplayName("Test Find Employee by Email")
    void testFindEmployeeByEmail() {
        employeeRepository.save(employee1);
        Optional<Employee> foundEmployee = employeeRepository.findByEmail("john@example.com");

        assertTrue(foundEmployee.isPresent());
        assertEquals("John Doe", foundEmployee.get().getName());
    }

    @Test
    @DisplayName("Test Find Employee by Non-Existent Email")
    void testFindEmployeeByNonExistentEmail() {
        Optional<Employee> foundEmployee = employeeRepository.findByEmail("nonexistent@example.com");

        assertFalse(foundEmployee.isPresent());
    }

    @Test
    @DisplayName("Test Find Employees by Department")
    void testFindEmployeesByDepartment() {
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        Employee employee3 = new Employee(null, "Robert Johnson", "robert@example.com", "IT");
        employeeRepository.save(employee3);

        List<Employee> itEmployees = employeeRepository.findByDepartment("IT");

        assertNotNull(itEmployees);
        assertEquals(2, itEmployees.size());
    }

    @Test
    @DisplayName("Test Update Employee")
    void testUpdateEmployee() {
        Employee savedEmployee = employeeRepository.save(employee1);
        savedEmployee.setName("John Updated");
        savedEmployee.setDepartment("HR");

        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        assertEquals("John Updated", updatedEmployee.getName());
        assertEquals("HR", updatedEmployee.getDepartment());
    }

    @Test
    @DisplayName("Test Delete Employee")
    void testDeleteEmployee() {
        Employee savedEmployee = employeeRepository.save(employee1);
        employeeRepository.deleteById(savedEmployee.getId());

        Optional<Employee> deletedEmployee = employeeRepository.findById(savedEmployee.getId());
        assertFalse(deletedEmployee.isPresent());
    }
}