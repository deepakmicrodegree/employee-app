package com.employee.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Employee Entity Model
 * Represents an employee in the system with id, name, email, and department
 */
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "department", nullable = false, length = 100)
    private String department;

    // Constructors
    /**
     * Default constructor
     */
    public Employee() {
    }

    /**
     * Constructor with all fields
     * @param id the employee id
     * @param name the employee name
     * @param email the employee email
     * @param department the employee department
     */
    public Employee(Long id, String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    // Getters and Setters
    /**
     * Get employee ID
     * @return the employee ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set employee ID
     * @param id the employee ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get employee name
     * @return the employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Set employee name
     * @param name the employee name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get employee email
     * @return the employee email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set employee email
     * @param email the employee email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get employee department
     * @return the employee department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Set employee department
     * @param department the employee department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    // toString
    /**
     * String representation of the employee
     * @return string representation
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    // equals
    /**
     * Check equality of two employees
     * @param o the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(department, employee.department);
    }

    // hashCode
    /**
     * Generate hash code for the employee
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, department);
    }
}
