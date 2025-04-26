# Workflow Framework

## Overview
The Workflow Framework is a Spring Boot-based application designed to manage and automate workflows efficiently. It provides features such as user management, role-based access control, and workflow versioning.

## Features
- User and Role Management
- Workflow Versioning
- Database Migration with Flyway
- Resilience4j Circuit Breaker Integration
- RESTful APIs with OpenAPI Documentation

## Prerequisites
- Java 17 or higher
- Maven 3.9.9 or higher
- MySQL 8.0 or higher

## Setup Instructions

### Clone the Repository
```bash
git clone https://github.com/cspridhitek/workflow-framework.git
cd workflow-framework
```

### Configure the Database
Update the `application.properties` file with your MySQL database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/workflow_db?createDatabaseIfNotExist=true
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
java -jar target/workflow-management-1.0.0.jar
```

The application will be accessible at `http://localhost:8081`.

## API Documentation
The API documentation is available at `http://localhost:8081/swagger-ui.html`.

## Testing
Run the tests using Maven:
```bash
mvn test
```

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the Apache License 2.0. See the LICENSE file for details.