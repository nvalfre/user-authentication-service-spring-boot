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

POST - /sing-up - genetare new token

```
curl --request POST \
--url http://localhost:8080/sign-up \
--header 'Content-Type: application/json' \
--data '{
"name": "John Doe",
"email": "johndoe@example.com",
"password": "Abc123456",
"phones": [
    {
        "number": 1234567890,
        "citycode": 123,
        "contrycode": "+1"
    }
]
}'
```

POST - /login
```
curl --request POST \
  --url http://localhost:8080/login \
  --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huZG9lQGV4YW1wbGUuY29tIiwiaWF0IjoxNjIzNTMwOTk0LCJleHAiOjE2MjM1MzE1OTR9.hgopXa3q0o5U6Cc5U5iqX9YdAfKk7AHf2x_qUJ10smU6eJCDKj3u3JiZVylC9EhqfrZV7nMxR1BcRrjJHd8b7A' \
  --header 'Content-Type: application/json' \
  --data '{
    "username": "johndoe@example.com"
}'
```


### Database configuration

Actually this demo is using an embedded H2 database.

## Author

**Nicolas Valfre**
