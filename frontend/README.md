# Employee CRUD Application - Frontend

## Angular Application

### Prerequisites
- Node.js 14+
- Angular CLI 14+
- npm 6+

### Installation

```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install
```

### Configuration

Update `src/app/services/employee.service.ts` with your backend URL:

```typescript
private apiUrl = 'http://localhost:8080/api/employees';
```

### Development Server

```bash
# Start the development server
npm start

# or
ng serve
```

Navigate to `http://localhost:4200/` in your browser.

### Build for Production

```bash
npm run build
```

### Project Structure

```
frontend/
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   └── employee-list/
│   │   │       ├── employee-list.component.ts
│   │   │       ├── employee-list.component.html
│   │   │       └── employee-list.component.css
│   │   ├── models/
│   │   │   └── employee.model.ts
│   │   ├── services/
│   │   │   └── employee.service.ts
│   │   ├── app.module.ts
│   │   ├── app.component.ts
│   │   └── app.component.html
│   └── main.ts
└── package.json
```

### Features

- Create new employee records
- Display all employees in a table format
- Edit existing employee records
- Delete employee records
- Form validation
- Responsive design
