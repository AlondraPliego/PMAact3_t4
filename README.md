# PMAact3_t4 — CRUD Spring Boot con relación JPA (Libro / Autor)

**Instituto Tecnológico de Oaxaca**

Proyecto: CRUD en Spring Boot conectado a MySQL, con una relación `@ManyToOne` / `@OneToMany` entre dos entidades (**Libro** y **Autor**), vistas propias con Thymeleaf y despliegue en VPS.

**Materia**: Programación Web

**Profesora**: Adelina Martinez Nieto

**Alumna**: Pliego Mendez Alondra

## Objetivo

Que el estudiante construya un CRUD completo en Spring Boot conectado a una base de datos real (MySQL), implementando al menos una relación entre entidades, y probándolo con sus propias vistas dentro del mismo proyecto y con Postman/Bruno, o ambos — todo dentro del mismo proyecto Spring Boot, sin crear un proyecto de frontend separado (como React) todavía.

---

## Descripción del proyecto

Este proyecto es un sistema CRUD (Crear, Leer, Actualizar, Eliminar) para la gestión simulada de una pequeña biblioteca, desarrollado con Spring Boot 4 y Spring Data JPA, conectado a una base de datos MySQL local. Permite registrar autores y libros, relacionando cada libro con su autor correspondiente mediante una relación muchos a uno. El proyecto cuenta con vistas propias construidas con Thymeleaf usando @Controller en vez de @RestController con el objetivo de tener mayor facilidad para la presentación de las vistas y la llamada a los endpoints, incluye un buscador de libros por título, y valida los datos antes de guardarlos, mostrando mensajes de confirmación o error según es necesario. El proyecto está desplegado en un VPS con HTTPS mediante sslip.io para mantener la parte de la seguridad.

---

## Entidades y relación

- **Entidades:** `Libro` y `Autor`
- **Tipo de relación:** Muchos a uno `@ManyToOne` en Libro de forma unidireccional — un Autor puede tener muchos Libros, un Libro pertenece a un solo Autor
- **Llave foránea:** `autor_id` en la tabla `libro`

---

## Tecnologías 

- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- Thymeleaf
- MySQL 8.0
- Maven

---

## Capturas de pantalla

### Entidades relacionadas (Libro y Autor)
Autor
``` java
@Entity
@Table(name = "autor")
public class Autor {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
}
``` 

Libro
``` java
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Column(name = "año_publicacion")
    private Integer añoPublicacion;

    @Column(name = "genero")
    private String genero;

    @Column(name = "copias_disponibles")
    private Integer copiasDisponibles;
``` 
### CRUD Vista Inicial
![Inicio](img/General.jpeg)

### CRUD — Crear
![Crear1](img/addbutton.jpeg)

Muestra de la tabla en terminal de la base de datos del vps.

![Crear2](img/tablainicial.jpeg)

Si el autor no existe, el usuario tiene la opcion de crear uno nuevo para el libro que desee añadir, se corrobora la relación adecuada mediante el nombre del autor siendo mostrado en la tabla de libros.

![Crear3](img/formAutor.jpeg)

Formulario de libro con boton de guardar que es el llama al endpoint de post, para añadir un nuevo elemento a la lista.

![Crear4](img/formLibro.jpeg)

Confirmacion del nuevo elemento añadido

![Crear5](img/libroC.jpeg)
![Crear6](img/libroCT.jpeg)
![Crear7](img/pruebaDatos.jpeg)

### CRUD — Actualizar
Sobre cada elemento se pueden realizar dos acciones o editarlo o eliminarlo.

![ACT](img/editLibro.jpeg)

Confirmacion de la edicion sobre el elemento seleccionado.

![ACT2](img/libroeditC.jpeg)
![ACT3](img/libroeditT.jpeg)
![ACT4](img/libroeditTD.jpeg)

### CRUD — Eliminar
![DEL1](img/eliminarlibro.jpeg)

Confirmación de la eliminacion del elemento seleccionado.
![DEL2](img/clibro.jpeg)
![DEL3](img/celibrot.jpeg)
![DEL4](img/celibrotd.jpeg)

---

## Cómo correr el proyecto localmente

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

## Despliegue

- **Repositorio de GitHub:** https://github.com/AlondraPliego/PMAact3_t4
- **Proyecto corriendo en el VPS:** https://165-22-6-118.sslip.io
- **Colección de Postman/Bruno:** 
---

## Preguntas de Classroom

**a) ¿Qué dos entidades elegiste y qué tipo de relación implementaste entre ellas?**

Elegí las entidades **Libro** y **Autor**. La relación implementada es de **uno a 
muchos / muchos a uno**: un Autor puede tener muchos libros asociados, pero cada 
libro pertenece a un único autor. En el código, esto se representa con la anotación 
`@ManyToOne` en la entidad `Libro`, usando `@JoinColumn(name ="autor_id")` para definir la llave foránea correspondiente. La relación se implementó de forma unidireccional, ya que el proyecto no requiere navegar desde un autor hacia su lista de libros directamente. 

**b) ¿Qué es un Repository en Spring Data JPA y qué ventaja tiene sobre escribir tú mismo las consultas SQL?**

Un Repository en Spring Data JPA es una interfaz que extiende `JpaRepository<T, ID>` lo que le permite a la aplicación interactuar con la base de datos sin necesidad de escribir manualmente las consultas SQL. Spring genera automáticamente la implementación en tiempo de ejecución, proporcionando métodos ya listos para usar como `findAll()`, `save()`, `findById()` y `deleteById()`. Además, permite crear consultas personalizadas con solo declarar el nombre del método siguiendo una 
convención (por ejemplo, `findByTituloContaining(String titulo)`), y Spring Data JPA interpreta ese nombre y genera la consulta SQL correspondiente automáticamente. La principal ventaja sobre escribir SQL manualmente es que reduce la cantidad de codigo que se necesita para la realizacion de consultas, además de que simplifica las consultas con solo una o dos palabras porque lo que tampoco es dificil de recordar. 

**c) ¿Por qué es una buena práctica separar la lógica en una capa de Service en vez de ponerla directamente en el Controller?**
Servicios es la capa que encapsula la lógica de negocio  se comunica con la capa de datos.
Separar la lógica en una capa de Service mantiene al Controller enfocado exclusivamente en su responsabilidad: recibir peticiones HTTP y decidir qué vista o respuesta devolver, sin mezclarse con las reglas de negocio ni con el acceso a 
datos. 
Esta separación también facilita la reutilización de la lógica de negocio, simplifica las pruebas unitarias al poder probar la lógica de negocio de forma aislada sin depender de la capa web, y hace el proyecto más fácil de mantener y escalar a futuro, ya que los cambios en las reglas de negocio no requieren tocar el Controller.

## Fuentes de Consulta
- Bezkoder. (2023, 3 octubre). JPA One To Many example with Hibernate and Spring Boot - BezKoder. BezKoder. https://www.bezkoder.com/jpa-one-to-many/#google_vignette
- CodingNomads. (s. f.). Spring Data JPA Repositories for Efficient Database Operations. https://codingnomads.com/spring-data-jpa-repository
- EdTics Academy. (2026, 29 marzo). Crear CRUD con Spring Boot, Java y MySQL en NetBeans | Paso a Paso 2026 [Vídeo]. YouTube. https://www.youtube.com/watch?v=uTwxbOnwB9k

