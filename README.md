# ShorterURL 🌐✂️

¡Bienvenido a **ShorterURL**! 🎉  
Este es un proyecto simple pero poderoso: una API diseñada para acortar URLs largas y convertirlas en enlaces más manejables y fáciles de compartir. Perfecto para desarrolladores que buscan integrar funcionalidades de acortamiento de enlaces en sus aplicaciones. 🚀

---

## ¿Qué hace ShorterURL? 🤔

- **Acorta URLs largas**: Convierte enlaces extensos en versiones más cortas y fáciles de recordar.  
- **Fácil de usar**: Una API sencilla y bien estructurada para integrar en tus proyectos.  
- **Escalable**: Diseñado para crecer contigo, con la posibilidad de agregar más funcionalidades en el futuro.  

---

## Características principales ✨

- **Generación de URLs cortas**: Crea enlaces únicos y compactos a partir de URLs largas.  
- **Redirección inteligente**: Accede a la URL original a través del enlace acortado.  
- **Próximamente**: Estadísticas de uso, personalización de enlaces y más. 🚀  

---

# URL Shortener API

Esta API permite acortar URLs largas, obtener información sobre las URLs acortadas, actualizarlas, eliminarlas y redirigir a la URL original mediante un shortCode.

## 📌 Endpoints

### **1️⃣ Acortar una URL**
**Endpoint:**
```http
POST /shorten 
```
**Descripción:** Acorta una URL y devuelve un shortCode único.

**Cuerpo de la solicitud:**
```json
{
    "url": "https://github.com/Guadapf"
}
```

**Respuesta:**
```json
{
    "idUrl": 6,
    "url": "https://github.com/Guadapf",
    "shortCode": "2Vg2rl",
    "createdAt": "2025-02-26T14:32:22",
    "updatedAt": "2025-02-26T14:32:22"
}
```
**Códigos de respuesta:**
- `201 Created`: URL acortada con éxito.
- `400 Bad Request`: Error en la solicitud.

---

### **2️⃣ Obtener información de una URL acortada**
**Endpoint:**
```http
GET /shorten/{shortCode}
```
**Descripción:** Obtiene los detalles de una URL acortada a partir de su shortCode.

**Ejemplo de respuesta:**
```json
{
    "idUrl": 6,
    "url": "https://github.com/Guadapf",
    "shortCode": "2Vg2rl",
    "createdAt": "2025-02-26T14:32:22",
    "updatedAt": "2025-02-26T14:32:22"
}
```
**Códigos de respuesta:**
- `200 OK`: Se encontró la URL.
- `404 Not Found`: No se encontró la URL.

---

### **3️⃣ Actualizar una URL acortada**
**Endpoint:**
```http
PUT /shorten/{shortCode}
```
**Descripción:** Actualiza la URL asociada a un shortCode.

**Cuerpo de la solicitud:**
```json
{
    "url": "https://nueva-url.com"
}
```
**Ejemplo de respuesta:**
```json
{
    "idUrl": 6,
    "url": "https://nueva-url.com",
    "shortCode": "2Vg2rl",
    "createdAt": "2025-02-26T14:32:22",
    "updatedAt": "2025-02-27T10:00:00"
}
```
**Códigos de respuesta:**
- `200 OK`: URL actualizada con éxito.
- `400 Bad Request`: Error en la solicitud.
- `404 Not Found`: No se encontró la URL.

---

### **4️⃣ Eliminar una URL acortada**
**Endpoint:**
```http
DELETE /shorten/{shortCode}
```
**Descripción:** Elimina una URL acortada de la base de datos.

**Códigos de respuesta:**
- `204 No Content`: URL eliminada con éxito.
- `404 Not Found`: No se encontró la URL.

---

### **5️⃣ Redirigir a la URL original**
**Endpoint:**
```http
http://localhost:{port}/shorten/r/{shortCode}
```
**Descripción:** Redirige automáticamente a la URL original a partir de un shortCode.

**Códigos de respuesta:**
- `302 Found`: Redirección exitosa.
- `404 Not Found`: No se encontró la URL.

---

## 🚀 Cómo ejecutar la API
1. Clonar este repositorio.
2. Configurar la base de datos en `application.properties`.
3. Ejecutar el proyecto con:
   ```sh
   mvn spring-boot:run
   ```
4. La API estará disponible en `http://localhost:8080/`.

---

## 🛠 Tecnologías utilizadas
- Java 17
- Spring Boot
- JPA/Hibernate
- MySQL

---

## Contribuciones 🤝

¡Las contribuciones son bienvenidas! Si tienes ideas para mejorar ShorterURL o encuentras algún problema, no dudes en abrir un *issue* o enviar un *pull request*. Juntos podemos hacer que ShorterURL sea aún mejor. 💪  

---

## Licencia 📜

Este proyecto está bajo la licencia **MIT**. Siéntete libre de usarlo, modificarlo y compartirlo. ¡Solo recuerda dar crédito! 😊  

---

## Contacto 📩

¿Tienes preguntas o sugerencias? ¡Nos encantaría escucharte!  
Puedes contactarnos a través de guadapfernandez@gmail.com.  

---

Proyecto creado a partir del siguiente link https://roadmap.sh/projects/url-shortening-service  
¡Gracias por visitar ShorterURL! Esperamos que disfrutes usando esta herramienta tanto como nosotros disfrutamos creándola. 💙  

✨ **Acorta, comparte y simplifica.** ✨

