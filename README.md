# Corporate Asset Management System (CAMS)

A full-stack web application for IT Administrators to manage company hardware inventory and track asset assignments to employees.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.12-brightgreen)
![Java](https://img.shields.io/badge/Java-17+-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-336791)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [API Endpoints](#-api-endpoints)
- [Project Structure](#-project-structure)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)

## ✨ Features

- **Employee Management**
  - Add new employees with name, email, and department
  - View all employees in a directory
  - See assets assigned to each employee

- **Asset Management**
  - Add new assets (laptops, monitors, keyboards, etc.)
  - Track asset serial numbers
  - View complete asset inventory

- **Asset Assignment**
  - Assign available assets to employees
  - Unassign assets from employees
  - Real-time status tracking (AVAILABLE/ASSIGNED)

- **Business Rules**
  - Composite unique constraint: Same asset type cannot have duplicate serial numbers
  - Different asset types can share serial numbers (e.g., Laptop SN001 and Monitor SN001)

## 🛠 Tech Stack

### Backend
- **Java 17+** - Programming language
- **Spring Boot 3.4.x** - Application framework
- **Spring Data JPA** - Data access layer
- **Hibernate** - ORM framework
- **PostgreSQL** - Relational database
- **Lombok** - Reduce boilerplate code
- **Maven** - Build tool

### Frontend
- **HTML5** - Markup
- **Bootstrap 5.3** - CSS framework
- **jQuery 3.7** - JavaScript library
- **Bootstrap Icons** - Icon library

## 📦 Prerequisites

Before running this application, ensure you have:

- **JDK 17 or higher** - [Download](https://adoptium.net/)
- **PostgreSQL 15+** - [Download](https://www.postgresql.org/download/)
- **Maven 3.8+** (or use included Maven wrapper)
- **Git** - [Download](https://git-scm.com/)

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/asset-management.git
   cd asset-management
   ```

2. **Create PostgreSQL database**
   ```sql
   CREATE DATABASE asset_db;
   ```

3. **Configure database connection** (see [Configuration](#-configuration))

4. **Build the project**
   ```bash
   # Windows
   .\mvnw.cmd clean install
   
   # Linux/Mac
   ./mvnw clean install
   ```

## ⚙ Configuration

Update `src/main/resources/application.properties` with your database credentials:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/asset_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## 🏃 Running the Application

### Using Maven Wrapper

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Using JAR file

```bash
# Build JAR
.\mvnw.cmd clean package -DskipTests

# Run JAR
java -jar target/asset-management-0.0.1-SNAPSHOT.jar
```

The application will start at: **http://localhost:8080**

## 📡 API Endpoints

### Employees

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/employees` | Get all employees |
| `POST` | `/api/employees` | Create new employee |

### Assets

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/assets` | Get all assets |
| `POST` | `/api/assets` | Create new asset |
| `PUT` | `/api/assets/{id}/assign/{empId}` | Assign asset to employee |
| `PUT` | `/api/assets/{id}/unassign` | Unassign asset |

### Example Requests

**Create Employee**
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"fullName":"John Doe","email":"john@example.com","department":"IT"}'
```

**Create Asset**
```bash
curl -X POST http://localhost:8080/api/assets \
  -H "Content-Type: application/json" \
  -d '{"assetName":"Laptop","serialNumber":"LT-2025-001"}'
```

**Assign Asset**
```bash
curl -X PUT http://localhost:8080/api/assets/1/assign/1
```

## 📁 Project Structure

```
asset-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/asset_management/
│   │   │   ├── controller/
│   │   │   │   └── AssetController.java
│   │   │   ├── dto/
│   │   │   │   └── AssetDTO.java
│   │   │   ├── model/
│   │   │   │   ├── Asset.java
│   │   │   │   ├── AssetStatus.java
│   │   │   │   └── Employee.java
│   │   │   ├── repository/
│   │   │   │   ├── AssetRepository.java
│   │   │   │   └── EmployeeRepository.java
│   │   │   ├── service/
│   │   │   │   └── AssetService.java
│   │   │   └── AssetManagementApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── style.css
│   │       │   ├── index.html
│   │       │   ├── employees.html
│   │       │   ├── assets.html
│   │       │   ├── employees-list.html
│   │       │   └── assets-list.html
│   │       └── application.properties
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```

## 📸 Screenshots

### Landing Page
Beautiful dashboard with navigation cards for all features:
- Add Employee
- Add Asset
- Employees Directory
- Assets Inventory

### Employees Directory
View all employees with their assigned assets including serial numbers.

### Assets Inventory
Complete asset list with status badges and assign/unassign actions.

## 🏗 Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                      Frontend (Static)                       │
│              HTML + Bootstrap + jQuery + AJAX               │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                    Controller Layer                          │
│              @RestController + @RequestMapping              │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                     Service Layer                            │
│              @Service + @Transactional                      │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                   Repository Layer                           │
│              JpaRepository + Spring Data                    │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      PostgreSQL                              │
│                 employee + asset tables                     │
└─────────────────────────────────────────────────────────────┘
```

## 🔮 Future Improvements

- [ ] Add input validation with Bean Validation
- [ ] Implement global exception handling
- [ ] Add authentication with Spring Security
- [ ] Implement pagination for large datasets
- [ ] Add search and filter functionality
- [ ] Write unit and integration tests
- [ ] Add Swagger/OpenAPI documentation
- [ ] Dockerize the application

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your Name](https://linkedin.com/in/yourprofile)

---

⭐ Star this repository if you found it helpful!
