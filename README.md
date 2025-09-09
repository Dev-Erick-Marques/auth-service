# Auth Service

Microservice responsible for user authentication and authorization using Spring Boot, Spring Security, and JWT. 
Part of the Elevare Commerce microservices ecosystem.

[Read in portuguese](README-PT.md)

## Technologies
- Java 17+
- Spring Boot 3
- Spring Security
- Spring Data JPA 
- PostgreSQL 
- JWT (JSON Web Token)
- OAuth2 Resource Server 
- Maven

## Features
- Credential authentication (/auth/login)
- User registration (/auth/register)
- JWT token generation and validation 

## Main Endpoints
| Método | Endpoint            | Descrição           |
| ------ | ------------------- |---------------------|
| POST   | `/auth/login`       | Perform login       |
| POST   | `/auth/register`    | Register a new user |

## Next Steps
- Implement refresh token 
- Add API documentation with Swagger/OpenAPI
