-- Create Database
CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

-- Create Employee Table
CREATE TABLE IF NOT EXISTS employees (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  department VARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Index on email for faster queries
CREATE INDEX idx_email ON employees(email);
CREATE INDEX idx_department ON employees(department);

-- Sample Data (Optional)
INSERT INTO employees (name, email, department) VALUES 
('John Doe', 'john.doe@example.com', 'IT'),
('Jane Smith', 'jane.smith@example.com', 'HR'),
('Robert Johnson', 'robert.johnson@example.com', 'Sales'),
('Emily Davis', 'emily.davis@example.com', 'Finance');