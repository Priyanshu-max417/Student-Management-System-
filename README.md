# Student Management System

A REST API for creating, viewing, updating, and deleting student records. The application is built with Spring Boot and stores student data in PostgreSQL.

## Features

- Create a student with a name and email address
- List all students or retrieve one by ID
- Replace or partially update a student
- Delete a student
- Persist data with Spring Data JPA and PostgreSQL
- Validate student creation requests

## Tech stack

- Java 25
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Maven Wrapper

## Prerequisites

- JDK 25
- PostgreSQL

## Run locally

1. Create a PostgreSQL database named `StudentDB`:

   ```sql
   CREATE DATABASE "StudentDB";
   ```

2. Set the database password in PowerShell (use the password for your PostgreSQL user):

   ```powershell
   $env:DB_PASSWORD = "your-postgres-password"
   ```

   The configured database user defaults to `postgres`. To use another user, set `DB_USERNAME` as well. If your PostgreSQL server or database uses different connection settings, update `spring.datasource.url` in `src/main/resources/application.properties`.

3. Start the application from the project directory:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

The API is available at `http://localhost:8082/api`. The server port and `/api` context path are configured in `application.properties`.

## API

All endpoints use the `/students` path under the `/api` context path.

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/students` | List all students |
| `GET` | `/api/students/{id}` | Get a student by ID |
| `POST` | `/api/students` | Create a student |
| `PUT` | `/api/students/{id}` | Replace a student's name and email |
| `PATCH` | `/api/students/{id}` | Update the student's name and/or email |
| `DELETE` | `/api/students/{id}` | Delete a student |

### Create a student

```http
POST /api/students
Content-Type: application/json
```

```json
{
  "name": "Aarav Sharma",
  "email": "aarav@example.com"
}
```

The name must contain 3–30 characters, and the email must be valid. A successful request returns `201 Created` with the created student, including its generated ID.

### Partially update a student

Send one or both supported fields (`name` and `email`) in the request body:

```json
{
  "email": "aarav.sharma@example.com"
}
```

### Example requests

```powershell
curl.exe http://localhost:8082/api/students
curl.exe http://localhost:8082/api/students/1
curl.exe -X DELETE http://localhost:8082/api/students/1
```

## Build and test

Run the tests with PostgreSQL running, the `StudentDB` database created, and `DB_PASSWORD` set as above. The context-load test connects to the configured database.

```powershell
.\mvnw.cmd test
```

Package the application:

```powershell
.\mvnw.cmd clean package
```
