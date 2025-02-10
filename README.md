# JwtSecurity

Sistema de Seguridad basado en JWT utilizando Spring Boot.

## Descripción

Este proyecto implementa un sistema de autenticación y autorización basado en JSON Web Tokens (JWT) utilizando Spring Boot. Los usuarios pueden registrarse, iniciar sesión y acceder a endpoints protegidos mediante tokens JWT.

## Requisitos

- Java 17
- Maven

## Configuración

1. Clona el repositorio:
    ```sh
    git clone <URL_DEL_REPOSITORIO>
    cd JwtSecurity
    ```

2. Configura las propiedades de la aplicación en `src/main/resources/application.properties`:
    ```ini
    # Configuración del puerto del servidor
    server.port=8081

    # Propiedades para JWT
    jwt.secret=YjURpmFMjkcw4Ltx8hfzC60U6JZJ7MLSjduhtsJt7ZA/yj+qJW6h75rOXfIS4ecjsZVJMt/cs3F1vLro4Rlf4w==
    jwt.expiration=3600000
    jwt.refreshThreshold=600000

    # Configuración de perfiles
    spring.profiles.active=dev

    # Datos personalizados
    app.author=gusgonza
    app.organization=Ilerna Barcelona
    app.description=Sistema de Seguridad basado en JWT
    app.contactEmail=gusgonza@myyahoo.com
    ```

3. Compila y ejecuta la aplicación:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

## Uso

### Registro de Usuario

- **Endpoint**: `/auth/register`
- **Método**: `POST`
- **Cuerpo de la solicitud**:
    ```json
    {
      "username": "cloud"
    }
    ```
- **Respuesta**:
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJjbG91ZCIsImlhdCI6MTczOTE5MDk3MCwiZXhwIjoxNzM5MTk0NTcwfQ.HGMU4LLpbNIhysrcebwIFolBvRBIVF8RAle-VpDHQms"
    }
    ```

### Inicio de Sesión

- **Endpoint**: `/auth/login`
- **Método**: `POST`
- **Cuerpo de la solicitud**:
    ```json
    {
      "username": "cloud"
    }
    ```
- **Respuesta**:
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJjbG91ZCIsImlhdCI6MTczOTE5MDk3MCwiZXhwIjoxNzM5MTk0NTcwfQ.HGMU4LLpbNIhysrcebwIFolBvRBIVF8RAle-VpDHQms"
    }
    ```

### Acceso a Endpoints Protegidos

- **Endpoint**: `/protected`
- **Método**: `GET`
- **Encabezado**:
    - `Authorization: Bearer <tu_token_jwt>`

## Dependencias

- Spring Boot Starter Web
- Spring Boot Starter Security
- Java JWT de Auth0
- JJWT
- Spring Boot Starter Validation
- Lombok
- Spring Boot Actuator

## Autor

- **Nombre**:  [gusgonza](https://github.com/gusgonza42)
- **Organización**: Ilerna Barcelona
- **Correo de contacto**: gusgonza@myyahoo.com