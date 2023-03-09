# Spring Boot - User && Roles - JWT Security Demo

## About
Spring boot demo for user with roles using JWT and H2.

## Requirements
    - Language: Java
    - Spring Boot: 2.5.x
    - Group: com.nv
    - Artifact: user-authentication-service-spring-boot
    - Dependencies:
    - Spring Web
    - Spring Data JPA
    - H2 Database
    - Spring Security

## Usage
*mvn spring-boot:run*
[Localhost](http://localhost:8080/).

**H2-Console** admin: [H2-Console](http://localhost:8080/spring-boot-jwt-security/h2-console)

## Backend
POST - /oauth/token - genetare new token
GET  - /auth/role - returns all the valid roles to be used
POST - /api/v1/user - User adding API with unrestricted access(Require an username, password and Role)

### Database configuration
Actually this demo is using an embedded H2 database.

## Author
**Nicolas Valfre**
