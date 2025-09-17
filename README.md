# Auth Service

Microservice responsible for user authentication and authorization using Spring Boot, Spring Security, and JWT. 
Part of the Elevare Commerce microservices ecosystem.

[Read in portuguese](README-PT.md)

## Technologies
- Java 17+, Spring Boot 3, Spring Security,Spring Data JPA,PostgreSQL
- Consul, JWT (JSON Web Token), OAuth2 Resource Server, Maven

## Features
- Credential authentication (/auth/login)
- User registration (/auth/register)
- JWT token generation and validation
---

## Configurations
Before starting the application, the following configurations are required:

### Database
1. Create a database in your DBMS:
 ```
 CREATE DATABASE user_service;
 ```
2. Update the ``application.properties`` file with your ``credentials``.

````
   spring.datasource.url=jdbc:postgresql://localhost:5432/user_service
   spring.datasource.username=your_username
   spring.datasource.password=your_password
````
---
### RSA 256 key pair
The application uses JWT signed with RSA key pairs.

1. If you don’t have a key pair yet, you can generate one with this [project](https://github.com/Dev-Erick-Marques/rsa256-key-pair-generator).
2. Place the keys in the following location within the project:
````
   /auth-service
   └── src
       └── main
           └── resources
               └── keys
                   ├── public.pub
                   └── private.key 

````
⚠️ Não compartilhe sua chave privada nem as credenciais do ``application.properties``.

---

## Superadmin Credentials
For convenience, a **superadmin** user is pre-configured, so you can log in without creating an account manually.
- **Email:** `superadmin@auth.com`
- **Password:** `qCLU4dYfj6IsLyK5fE8h`

> These credentials are pre-set in the initial database for testing and development purposes.
---

## Main Endpoints
| Method | Endpoint         | Description         |
|--------|------------------|---------------------|
| POST   | `/auth/login`    | Perform login       |
| POST   | `/auth/register` | Register a new user |

---
## Use exemple
### Log in with Superadmin
1. POST ``/auth/login``
Content-Type: application/json
````
{
"email": "superadmin@auth.com",
"password": "qCLU4dYfj6IsLyK5fE8h"
}
````
2. The response will return a JWT token for authenticating subsequent requests:
````
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

````
---


## Next Steps
- Implement refresh token 
- Add API documentation with Swagger/OpenAPI
---
## Notes

- The superadmin has full privileges in the application. 
- For production, it is recommended to create individual users and change the superadmin password. 
- The ``/auth/register`` endpoint exists but is not necessary to use for initial testing.
- 