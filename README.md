# WorkHub_User_Service

The **WorkHub_User_Service** is a vital microservice within the **WorkHub** platform, responsible for handling user-related operations such as registration, profile updates, personalization, and password recovery. It integrates seamlessly with the **WorkHub_ApiGateway** to ensure secure and efficient communication within the microservices ecosystem.

## Features

- **User Registration**: Handles the creation of new user accounts with secure data storage.
- **User Profile Management**: Supports updating user details to keep profiles up to date.
- **Forgot Password**: Facilitates password recovery through a secure OTP mechanism.
- **Gateway Integration**: Aligns with **WorkHub_ApiGateway** for request routing and authentication.
- **Personalization**: Store user's interest regarding type of work.

## Tech Stack

- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: MongoDB, Redis
- **Authentication**: JSON Web Tokens (JWT)
- **Build Tool**: Maven
- **Containerization**: Docker

## Architecture

The `WorkHub_User_Service` is part of a microservices architecture. It communicates with other services, including the `WorkHub_ApiGateway`, to ensure user data integrity and efficient task distribution.

- **MongoDB**: Used for persistent storage of user data.
- **Redis**: Employed for caching and temporary storage, improving performance and reducing load on the database.
- **ApiGateway**: Acts as a mediator for secure and seamless communication between the service and other components of the WorkHub platform.

## Installation and Setup

### Prerequisites

1. **Java 19** installed on your machine.
2. **MongoDB** and **Redis** running locally or accessible via a remote server.
3. **Docker** (optional for containerized deployment).

### Clone the Repository

```bash
https://github.com/Manish-Kumarrrr/WorkHub_User_Service.git
cd WorkHub_User_Service
```

### Configuration: MongoDB and Redis Setup

Below is the configuration for **MongoDB** and **Redis** in the `application.yml` file. Ensure these settings are properly defined before running the service.

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/workhub_users
  redis:
    host: localhost
    port: 6379
    password: # Add password if Redis is secured; leave blank otherwise

```

### Run the Application
To start the service:

```bash
Copy code
mvn spring-boot:run
```
The service will run on http://localhost:8080.


This `README.md` provides a high-level overview of the service and its features without delving into specific API endpoint details.


