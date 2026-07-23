# Complete Installation Guide

## Employee CRUD Application Setup

### Step 1: Database Setup

1. Install MySQL Server (if not already installed)
2. Open MySQL command line or MySQL Workbench
3. Run the SQL script:

```bash
source database/mysql-setup.sql
```

Or execute the queries from `database/mysql-setup.sql` manually.

### Step 2: Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Step 3: Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

The frontend will start on `http://localhost:4200`

### Step 4: Testing the Application

1. Open your browser and navigate to `http://localhost:4200`
2. You should see the Employee Management System interface
3. Click "Add New Employee" to create a new employee
4. Fill in the form fields:
   - **Name**: Employee's full name
   - **Email**: Employee's email address
   - **Department**: Employee's department
5. Click "Create" button
6. View all employees in the table below
7. Click "Edit" to update an employee
8. Click "Delete" to remove an employee

## MySQL Connection String

### For Spring Boot Application
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Database Details
- **Database Name**: `employee_db`
- **Table Name**: `employees`
- **Columns**:
  - `id` (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
  - `name` (VARCHAR(100), NOT NULL)
  - `email` (VARCHAR(100), NOT NULL, UNIQUE)
  - `department` (VARCHAR(100), NOT NULL)
  - `created_at` (TIMESTAMP)
  - `updated_at` (TIMESTAMP)

## Troubleshooting

### MySQL Connection Error
- Ensure MySQL server is running
- Verify the connection string is correct
- Check username and password
- Ensure the database exists

### CORS Error
- Backend has CORS enabled for `http://localhost:4200`
- If using a different frontend URL, update the CORS configuration in `EmployeeController.java`

### Port Already in Use
- Backend port 8080: Change in `application.properties` → `server.port=8081`
- Frontend port 4200: Use `ng serve --port 4300`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| POST | `/api/employees` | Create new employee |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |
