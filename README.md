¡Entendido! Aquí tienes el `README` actualizado con la sección de integración que mencionamos antes, lista para que la
añadas a tu archivo:

---

# 🔐 JwtSecurity

Sistema de Seguridad basado en JWT utilizando Spring Boot.

## 📌 Descripción

Este proyecto implementa un sistema de autenticación y autorización basado en **JSON Web Tokens (JWT)** utilizando *
*Spring Boot**.  
Los usuarios pueden registrarse, iniciar sesión y acceder a endpoints protegidos mediante tokens JWT.

## 📋 Requisitos

✅ **Java 17**  
✅ **Maven**

## ⚙️ Configuración

1️⃣ **Clona el repositorio:**

```shell
git clone https://github.com/gusgonza42/jwt-security.git
```  

2️⃣ **Configura las propiedades de la aplicación en** `src/main/resources/application.properties`:

```ini
# 🌐 Configuración del servidor
server.port=8081

# 🔑 Propiedades para JWT
jwt.secret="CreateYourOwnSecretKey"
jwt.expiration=3600000
jwt.refreshThreshold=600000

# ⚙️ Configuración de perfiles
spring.profiles.active=dev
```  

3️⃣ **Compila y ejecuta la aplicación:**

```sh
mvn clean install
mvn spring-boot:run
```  

## 🚀 Uso

### 📝 Registro de Usuario

Puedes registrar con campos básicos como `username`, `email` y `password`.

🔹 **Endpoint**: `/auth/register`  
🔹 **Método**: `POST`  
🔹 **Cuerpo de la solicitud**:

```json
{
  "username": "cloud",
  "email": "cloud@example.com",
  "password": "password123"
}
```  

🔹 **Respuesta**:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```  

### 🔑 Inicio de Sesión

Puedes iniciar sesión con`username` y `password`. Para obtener un token JWT.

🔹 **Endpoint**: `/auth/login`  
🔹 **Método**: `POST`  
🔹 **Cuerpo de la solicitud**:

```json
{
  "username": "cloud",
  "password": "password123"
}
```

> [!TIP]
> 🔑 **Login flexible:** Puedes iniciar sesión tanto con tu `username` como con tu `email`.

```json
{
  "email": "cloud@example.com",
  "password": "password123"
}
```  

> [!IMPORTANT]  
> 📧 **Formato de correo válido:** El correo electrónico debe contener al menos una letra, un `@`, otra letra, un `.` y
> al menos dos letras después del punto.  
> ✅ **Ejemplo válido:** `usuario@dominio.com`  
> ❌ **Ejemplo inválido:** `usuario@dominio,com` (usa `,` en lugar de `.`)


🔹 **Respuesta**:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```  

### 🔐 Acceso a Endpoints Protegidos

Puedes acceder a los endpoints protegidos enviando el token JWT en el encabezado `Authorization`.

🔹 **Endpoint**: `/protected`  
🔹 **Método**: `GET`  
🔹 **Encabezado**:

```http
Authorization: Bearer <tu_token_jwt>
```  

## 🚀 Cómo Integrar en Tu Proyecto

1. **Clona este repositorio** en tu máquina local (sin el `.git` si solo necesitas los archivos del proyecto):

    ```sh
    git clone https://github.com/gusgonza42/jwt-security.git
    ```

   ```sh
    cd JwtSecurity
    rm -rf .git // Linux
    Remove-Item -Recurse -Force .git // Windows
    ```
   
   El comando `rm -rf .git` elimina el historial de Git, permitiéndote usar solo los archivos del proyecto en tu propia aplicación sin que se vincule al repositorio original.

2. **Integra el código en tu proyecto:**
    - Copia los archivos del proyecto de `JwtSecurity` a tu proyecto principal o agrega este repositorio como un submódulo de Git si prefieres mantenerlo en un solo repositorio pero independiente.
    - Asegúrate de configurar correctamente las dependencias en tu `pom.xml` (si estás usando Maven) para que se integren sin problemas.

3. **Configura las propiedades de la aplicación**:
   Abre el archivo `src/main/resources/application.properties` y ajusta las configuraciones de JWT según lo necesario:
    ```properties
    # Configuración del puerto del servidor
    server.port=8081

    # Propiedades para JWT
    jwt.secret=YjURpmFMjkcw4Ltx8hfzC60U6JZJ7MLSjduhtsJt7ZA/yj+qJW6h75rOXfIS4ecjsZVJMt/cs3F1vLro4Rlf4w==
    jwt.expiration=3600000
    jwt.refreshThreshold=600000
    ```

4. **Ejecuta la aplicación**:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

---

## 📦 Dependencias

📌 Spring Boot Starter Web  
📌 Spring Boot Starter Security  
📌 Java JWT de Auth0  
📌 JJWT  
📌 Spring Boot Starter Validation  
📌 Lombok  
📌 Spring Boot Starter Data JPA  
📌 MySQL Connector  
📌 Spring Boot Actuator  
📌 Spring Boot Starter Test

## 👨‍💻 Autor

- **📝 Nombre**: [gusgonza](https://github.com/gusgonza42)
- **📧 Correo de contacto**: gusgonza@myyahoo.com
