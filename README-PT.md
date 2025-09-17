# Auth Service

Microsserviço responsável por autenticação e autorização de usuários utilizando Spring Boot, Spring Security e JWT.
Faz parte do ecossistema de microsserviços do projeto Elevare Commerce.

## Tecnologias
- Java 17+, Spring Boot 3, Spring Security, Spring Data JPA, 
- PostgreSQL, JWT (JSON Web Token), Oauth2 Resource Server, Maven
---
## Funcionalidades
- Autenticação de credenciais (auth/login)
- Geração e validação de tokens JWT
- Registro de novos usuários
---
## Configurations
Antes de iniciar a aplicação, deve realizar as reguintes configurações:



### Banco de Dados

1. Crie uma base de dados no seu SGBD:
 ```
 CREATE DATABASE user_service;
 ```
2. Edite o arquivo ``application.properties`` com suas credenciais do banco.

````
   spring.datasource.url=jdbc:postgresql://localhost:5432/user_service
   spring.datasource.username=your_username
   spring.datasource.password=your_password
````
---

### Par de chaves RSA
A aplicação utiliza JWT assinado com chaves RSA.

1. Se você ainda não tem um par de chaves, você pode gerar uma com este [projeto](https://github.com/Dev-Erick-Marques/rsa256-key-pair-generator).
2. Coloque as chaves no seguinte local dentro do projeto:
````
   /auth-service
   └── src
       └── main
           └── resources
               └── keys
                   ├── public.pub
                   └── private.key 

````
⚠️ Do not share your private key or the credentials in ``application.properties``.

---

## Endpoints Principais
### URL Base: `/auth`
| Método | Endpoint       | Descrição             |
| ------ | -------------- | --------------------- |
| POST   | `/login`       | Realizar login        |
| POST   | `/register`    | Cadastrar novo usuário|

## Exemplo de Login
1. POST ``/auth/login``
   Content-Type: application/json
````
{
"email": "superadmin@auth.com",
"password": "qCLU4dYfj6IsLyK5fE8h"
}
````
2. A requisição irá retornar um token para as proximas requisições;
````
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

````
---



## Próximos passos
- Implementação de refresh token
- Documentação com Swagger/OpenAPI

## Observações
- Superadmin tem todos os privilégios. 
- Para produção, crie usuários individuais e altere a senha do superadmin. 
- Endpoint ``/auth/register`` é opcional para testes iniciais.