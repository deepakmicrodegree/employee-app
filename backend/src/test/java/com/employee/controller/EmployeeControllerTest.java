package com.employee.controller;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@DisplayName("Employee Controller Tests")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee(1L, "John Doe", "john@example.com", "IT");
        employee2 = new Employee(2L, "Jane Smith", "jane@example.com", "HR");
    }

    @Test
    @DisplayName("Test Create Employee - Success")
    void testCreateEmployee_Success() throws Exception {
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee1);

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john@example.com")))
                .andExpect(jsonPath("$.department", is("IT")));

        verify(employeeService, times(1)).createEmployee(any(Employee.class));
    }

    @Test
    @DisplayName("Test Get All Employees")
    void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].name", is("Jane Smith")));

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    @DisplayName("Test Get Employee by ID - Success")
    void testGetEmployeeById_Success() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee1));

        mockMvc.perform(get("/api/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john@example.com")));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    @DisplayName("Test Get Employee by ID - Not Found")
    void testGetEmployeeById_NotFound() throws Exception {
        when(employeeService.getEmployeeById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/employees/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).getEmployeeById(999L);
    }

    @Test
    @DisplayName("Test Update Employee - Success")
    void testUpdateEmployee_Success() throws Exception {
        Employee updatedEmployee = new Employee(1L, "John Updated", "john.updated@example.com", "HR");
        when(employeeService.updateEmployee(anyLong(), any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/api/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Updated")))
                .andExpect(jsonPath("$.department", is("HR")));

        verify(employeeService, times(1)).updateEmployee(anyLong(), any(Employee.class));
    }

    @Test
    @DisplayName("Test Update Employee - Not Found")
    void testUpdateEmployee_NotFound() throws Exception {
        Employee updatedEmployee = new Employee(null, "John Updated", "john.updated@example.com", "HR");
        when(employeeService.updateEmployee(anyLong(), any(Employee.class))).thenReturn(null);

        mockMvc.perform(put("/api/employees/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).updateEmployee(anyLong(), any(Employee.class));
    }

    @Test
    @DisplayName("Test Delete Employee - Success")
    void testDeleteEmployee_Success() throws Exception {
        when(employeeService.deleteEmployee(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).deleteEmployee(1L);
    }

    @Test
    @DisplayName("Test Delete Employee - Not Found")
    void testDeleteEmployee_NotFound() throws Exception {
        when(employeeService.deleteEmployee(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/employees/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).deleteEmployee(999L);
    }

    @Test
    @DisplayName("Test Get All Employees - Empty List")
    void testGetAllEmployees_EmptyList() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(employeeService, times(1)).getAllEmployees();
    }
}