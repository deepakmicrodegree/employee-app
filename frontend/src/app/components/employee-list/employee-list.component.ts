import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[] = [];
  selectedEmployee: Employee | null = null;
  showForm: boolean = false;
  isEdit: boolean = false;

  newEmployee: Employee = {
    name: '',
    email: '',
    department: ''
  };

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.loadEmployees();
  }

  // Load all employees
  loadEmployees(): void {
    this.employeeService.getAllEmployees().subscribe({
      next: (data) => {
        this.employees = data;
      },
      error: (error) => {
        console.error('Error loading employees:', error);
      }
    });
  }

  // Create Employee
  createEmployee(): void {
    if (this.newEmployee.name && this.newEmployee.email && this.newEmployee.department) {
      this.employeeService.createEmployee(this.newEmployee).subscribe({
        next: (data) => {
          console.log('Employee created:', data);
          this.loadEmployees();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error creating employee:', error);
        }
      });
    }
  }

  // Update Employee
  updateEmployee(): void {
    if (this.selectedEmployee && this.selectedEmployee.id) {
      this.employeeService.updateEmployee(this.selectedEmployee.id, this.selectedEmployee).subscribe({
        next: (data) => {
          console.log('Employee updated:', data);
          this.loadEmployees();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error updating employee:', error);
        }
      });
    }
  }

  // Delete Employee
  deleteEmployee(id: number | undefined): void {
    if (id && confirm('Are you sure you want to delete this employee?')) {
      this.employeeService.deleteEmployee(id).subscribe({
        next: () => {
          console.log('Employee deleted');
          this.loadEmployees();
        },
        error: (error) => {
          console.error('Error deleting employee:', error);
        }
      });
    }
  }

  // Edit Employee
  editEmployee(employee: Employee): void {
    this.selectedEmployee = { ...employee };
    this.isEdit = true;
    this.showForm = true;
  }

  // Show Create Form
  showCreateForm(): void {
    this.newEmployee = {
      name: '',
      email: '',
      department: ''
    };
    this.isEdit = false;
    this.selectedEmployee = null;
    this.showForm = true;
  }

  // Reset Form
  resetForm(): void {
    this.newEmployee = {
      name: '',
      email: '',
      department: ''
    };
    this.selectedEmployee = null;
    this.showForm = false;
    this.isEdit = false;
  }
}