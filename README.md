# PMAact3_t4 — CRUD Spring Boot con relación JPA (Libro / Autor)

Proyecto de la Actividad 3: CRUD en Spring Boot conectado a MySQL, con una relación `@ManyToOne` / `@OneToMany` entre dos entidades (**Libro** y **Autor**), vistas propias con Thymeleaf y despliegue en VPS.

---

## 📋 Descripción del proyecto

<!-- Escribe aquí 2-3 líneas explicando qué hace tu proyecto -->

---

## 🗂️ Entidades y relación

- **Entidades:** `Libro` y `Autor`
- **Tipo de relación:** Uno a muchos / muchos a uno (`@OneToMany` en Autor unidireccional... o `@ManyToOne` en Libro) — un Autor puede tener muchos Libros, un Libro pertenece a un solo Autor
- **Llave foránea:** `autor_id` en la tabla `libro`

---

## 🛠️ Tecnologías utilizadas

- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- Thymeleaf
- MySQL 8.0
- Maven

---

## 📸 Capturas de pantalla

### Entidades relacionadas (Libro y Autor)
<!-- Captura de tu Libro.java y Autor.java, o de las tablas en MySQL con SHOW TABLES / DESCRIBE -->

### CRUD — Crear
<!-- Captura del formulario de agregar libro lleno + confirmación de guardado -->

### CRUD — Leer
<!-- Captura de la tabla/lista de libros mostrando datos -->

### CRUD — Actualizar
<!-- Captura del formulario de editar un libro con datos precargados -->

### CRUD — Eliminar
<!-- Captura de antes/después de eliminar un libro -->

### Relación reflejada correctamente
<!-- Captura de la tabla de libros mostrando el NOMBRE del autor, no el ID -->

---

## 🚀 Cómo correr el proyecto localmente

```bash
git clone https://github.com/AlondraPliego/PMAact3_t4.git
cd PMAact3_t4
```

1. Crea una base de datos MySQL local llamada `PMAact3_crud`
2. Copia `application.properties.example` a `application.properties` y coloca tus propias credenciales
3. Corre el proyecto:

```bash
mvn spring-boot:run
```

4. Abre en el navegador: `http://localhost:8080/`

---

## 🌐 Despliegue

- **Repositorio de GitHub:** https://github.com/AlondraPliego/PMAact3_t4
- **Proyecto corriendo en el VPS:** <!-- http://TU_IP:PUERTO/ -->
- **Colección de Postman/Bruno:** <!-- enlace o archivo adjunto -->

---

## ❓ Preguntas de Classroom

**a) ¿Qué dos entidades elegiste y qué tipo de relación implementaste entre ellas?**

<!-- Tu respuesta -->

**b) ¿Qué es un Repository en Spring Data JPA y qué ventaja tiene sobre escribir tú mismo las consultas SQL?**

<!-- Tu respuesta -->

**c) ¿Por qué es una buena práctica separar la lógica en una capa de Service en vez de ponerla directamente en el Controller?**

<!-- Tu respuesta -->

**d) Enlaces**

- Repositorio de GitHub: <!-- link -->
- Proyecto corriendo en el VPS: <!-- link -->
- Colección de Postman/Bruno: <!-- link o archivo -->

---

## 👤 Autora

Alondra Pliego — PMA / Programación Web
