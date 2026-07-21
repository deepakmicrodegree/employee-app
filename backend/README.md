# Employee CRUD Application - Backend

## Spring Boot REST API

### Prerequisites
- Java 11+
- Maven 3.6+
- MySQL 5.7+

### Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE employee_db;
```

2. Run the SQL setup script from `database/mysql-setup.sql`

### Configuration

Update `src/main/resources/application.properties`:

```properties
# MySQL Connection String
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### API Endpoints

#### Create Employee
- **POST** `/api/employees`
- Body: `{ "name": "John", "email": "john@example.com", "department": "IT" }`

#### Get All Employees
- **GET** `/api/employees`

#### Get Employee by ID
- **GET** `/api/employees/{id}`

#### Update Employee
- **PUT** `/api/employees/{id}`
- Body: `{ "name": "John Updated", "email": "john@example.com", "department": "HR" }`

#### Delete Employee
- **DELETE** `/api/employees/{id}`

### Project Structure

```
backend/
├── src/main/java/com/employee/
│   ├── EmployeeApplication.java (Main class)
│   ├── model/
│   │   └── Employee.java
│   ├── repository/
│   │   └── EmployeeRepository.java
│   ├── service/
│   │   └── EmployeeService.java
│   └── controller/
│       └── EmployeeController.java
└── pom.xml
```
