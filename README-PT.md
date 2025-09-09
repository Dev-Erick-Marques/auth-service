# Auth Service

Microsserviço responsável por autenticação e autorização de usuários utilizando Spring Boot, Spring Security e JWT.
Faz parte do ecossistema de microsserviços do projeto Elevare Commerce.

## Tecnologias
- Java 17+
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Oauth2 Resource Server
- Maven

## Funcionalidades
- Autenticação de credenciais (auth/login)
- Geração e validação de tokens JWT
- Registro de novos usuários

## Endpoints Principais
| Método | Endpoint            | Descrição             |
| ------ | ------------------- | --------------------- |
| POST   | `/auth/login`       | Realizar login        |
| POST   | `/auth/register`    | Cadastrar novo usuário|


## Próximos passos
- Implementação de refresh token
- Documentação com Swagger/OpenAPI