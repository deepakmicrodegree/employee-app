package com.employee.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Employee Model Tests")
class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
    }

    @Test
    @DisplayName("Test Employee Creation with All Fields")
    void testEmployeeCreation() {
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john@example.com");
        employee.setDepartment("IT");

        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals("john@example.com", employee.getEmail());
        assertEquals("IT", employee.getDepartment());
    }

    @Test
    @DisplayName("Test Employee with Null Values")
    void testEmployeeWithNullValues() {
        employee.setId(null);
        employee.setName(null);
        employee.setEmail(null);
        employee.setDepartment(null);

        assertNull(employee.getId());
        assertNull(employee.getName());
        assertNull(employee.getEmail());
        assertNull(employee.getDepartment());
    }

    @Test
    @DisplayName("Test Employee Name Setter and Getter")
    void testEmployeeNameSetterGetter() {
        String testName = "Jane Smith";
        employee.setName(testName);
        assertEquals(testName, employee.getName());
    }

    @Test
    @DisplayName("Test Employee Email Setter and Getter")
    void testEmployeeEmailSetterGetter() {
        String testEmail = "jane.smith@example.com";
        employee.setEmail(testEmail);
        assertEquals(testEmail, employee.getEmail());
    }

    @Test
    @DisplayName("Test Employee Department Setter and Getter")
    void testEmployeeDepartmentSetterGetter() {
        String testDepartment = "HR";
        employee.setDepartment(testDepartment);
        assertEquals(testDepartment, employee.getDepartment());
    }

    @Test
    @DisplayName("Test Employee Constructor")
    void testEmployeeConstructor() {
        Employee newEmployee = new Employee(2L, "Robert Johnson", "robert@example.com", "Sales");

        assertEquals(2L, newEmployee.getId());
        assertEquals("Robert Johnson", newEmployee.getName());
        assertEquals("robert@example.com", newEmployee.getEmail());
        assertEquals("Sales", newEmployee.getDepartment());
    }

    @Test
    @DisplayName("Test Employee Equality")
    void testEmployeeEquality() {
        Employee emp1 = new Employee(1L, "John Doe", "john@example.com", "IT");
        Employee emp2 = new Employee(1L, "John Doe", "john@example.com", "IT");

        assertEquals(emp1, emp2);
    }
}