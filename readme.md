# ERP System

## Overview

The ERP System is a full-stack web application designed to streamline organizational and employee management through a centralized platform. The application provides secure authentication, user management, and administrative functionalities while maintaining a scalable and maintainable architecture.

Built using a modern technology stack, the project follows a client-server architecture with a React-based frontend and a Spring Boot backend. The system demonstrates full-stack development concepts including RESTful APIs, authentication and authorization, database integration, and responsive user interfaces.

## Features

### Authentication & Security

* Secure user login and authentication
* JWT-based authentication and authorization
* Protected API endpoints
* Role-based access control
* Secure password handling and validation

### User Management

* User registration and onboarding
* Profile management and updates
* User information storage and retrieval
* Administrative control over user records

### Employee Management

* Employee record management
* Employee information tracking
* Data organization and centralized access
* CRUD operations for employee-related data

### System Administration

* Administrative dashboard
* Centralized management capabilities
* Secure access to organizational data
* Scalable backend architecture

## Technology Stack

### Frontend

* React
* TypeScript
* Vite
* Modern CSS/UI components
* REST API integration

### Backend

* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Maven

### Database

* Relational database integration
* JPA/Hibernate ORM
* Persistent data storage

## Architecture

The project follows a layered architecture:

1. **Frontend Layer**

   * Handles user interaction and UI rendering
   * Communicates with backend APIs
   * Manages authentication state

2. **Backend Layer**

   * Processes business logic
   * Exposes RESTful API endpoints
   * Handles authentication and authorization
   * Manages database interactions

3. **Database Layer**

   * Stores application data
   * Maintains relationships and integrity
   * Supports efficient data retrieval

## Project Structure

```text
ERP-System/
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── frontend/
│   ├── src/
│   ├── package.json
│   └── ...
│
└── README.md
```

## Getting Started

### Prerequisites

* Java 17+
* Node.js
* npm
* Maven

### Backend Setup

```bash
cd backend
./mvnw spring-boot:run
```

The backend will start on:

```text
http://localhost:8080
```

### Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

The frontend will start on:

```text
http://localhost:5173
```

## Learning Outcomes

Through this project, I gained hands-on experience with:

* Full-stack application development
* REST API design and implementation
* JWT-based authentication
* Spring Security configuration
* Database integration using JPA/Hibernate
* Frontend-backend communication
* Modern React development practices
* Project organization and software architecture
* Version control using Git and GitHub

## Future Enhancements

* Advanced analytics dashboard
* Attendance management
* Payroll integration
* Department and team management
* Email notifications
* Audit logging
* Report generation
* Cloud deployment and containerization

## Author

Developed as a full-stack ERP application to explore enterprise software development concepts, secure authentication mechanisms, and scalable web application architecture.
