package com.employee.service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Service Tests")
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee(1L, "John Doe", "john@example.com", "IT");
        employee2 = new Employee(2L, "Jane Smith", "jane@example.com", "HR");
    }

    @Test
    @DisplayName("Test Create Employee")
    void testCreateEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);

        Employee createdEmployee = employeeService.createEmployee(employee1);

        assertNotNull(createdEmployee);
        assertEquals("John Doe", createdEmployee.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    @DisplayName("Test Get All Employees")
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test Get Employee by ID - Success")
    void testGetEmployeeById_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test Get Employee by ID - Not Found")
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeById(999L);

        assertFalse(result.isPresent());
        verify(employeeRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Test Update Employee - Success")
    void testUpdateEmployee_Success() {
        Employee updatedDetails = new Employee(null, "John Updated", "john.updated@example.com", "HR");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);

        Employee result = employeeService.updateEmployee(1L, updatedDetails);

        assertNotNull(result);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    @DisplayName("Test Update Employee - Not Found")
    void testUpdateEmployee_NotFound() {
        Employee updatedDetails = new Employee(null, "John Updated", "john.updated@example.com", "HR");
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        Employee result = employeeService.updateEmployee(999L, updatedDetails);

        assertNull(result);
        verify(employeeRepository, times(1)).findById(999L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    @DisplayName("Test Delete Employee - Success")
    void testDeleteEmployee_Success() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        boolean result = employeeService.deleteEmployee(1L);

        assertTrue(result);
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test Delete Employee - Not Found")
    void testDeleteEmployee_NotFound() {
        when(employeeRepository.existsById(999L)).thenReturn(false);

        boolean result = employeeService.deleteEmployee(999L);

        assertFalse(result);
        verify(employeeRepository, times(1)).existsById(999L);
        verify(employeeRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Test Get All Employees - Empty List")
    void testGetAllEmployees_EmptyList() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList());

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(employeeRepository, times(1)).findAll();
    }
}