# Spring Boot - User && Roles - JWT Security Demo

## About
Spring boot demo for user with roles using JWT and H2.

## Requirements
This demo is build with with Spring boot 2.4.4.

## Usage
*mvn spring-boot:run*
[Localhost](http://localhost:8080/).

**H2-Console** admin: [H2-Console](http://localhost:8080/spring-boot-jwt-security/h2-console)


## Backend
POST - /oauth/token - genetare new token
GET  - /auth/role - returns all the valid roles to be used
POST - /api/v1/user - User adding API with unrestricted access(Require an username, password and Role)

### Database configuration
Actually this demo is using an embedded H2 database that is automatically configured by Spring Boot. If you want to connect 
to another database you have to specify the connection in the *application.properties* in the resource directory. Here is the sample configuration for the purpose of demo:

```
### H2 Data source config ###
spring.datasource.url=jdbc:h2:mem:default
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto=none

### Enabling the H2 console ### 
spring.h2.console.enabled=true

```

## Author

**Nicolas Valfre**
