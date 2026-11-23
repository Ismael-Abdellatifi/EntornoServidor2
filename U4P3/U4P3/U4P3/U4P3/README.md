# U4P3 — Equipos de fútbol: aplicación web con persistencia

Aplicación Spring Boot para registrar **equipos de fútbol** (de barrio, amateur o federados)
y saber cuáles buscan jugadores. La página principal muestra todos los equipos registrados
y un formulario para añadir uno nuevo. Los datos se guardan en MariaDB, así que siguen ahí
al reiniciar la aplicación.

## 1. Dominio y modelo de datos

Entidad principal: **`Equipos`** (tabla `equipos`). No tiene relaciones con otras entidades.

| Atributo | Tipo Java | Tipo en MariaDB | Obligatorio | Qué representa | Reglas de validación |
|---|---|---|---|---|---|
| `id` | `Long` | `BIGINT AUTO_INCREMENT` | — (lo genera la BD) | Identificador del equipo | Generado automáticamente |
| `nombre` | `String` | `VARCHAR(60)` | Sí | Nombre del equipo | No vacío; máx. 60 caracteres |
| `ciudad` | `String` | `VARCHAR(50)` | Sí | Ciudad o localidad donde juega | No vacía; máx. 50 caracteres |
| `estadio` | `String` | `VARCHAR(80)` | No | Estadio o campo donde juega como local | Máx. 80 caracteres; si se deja vacío se guarda `NULL` |
| `fechaFundacion` | `LocalDate` | `DATE` | Sí | Fecha en que se fundó el equipo | Obligatoria; no puede ser futura |
| `numeroJugadores` | `Integer` | `INT` | Sí | Jugadores que tiene la plantilla | Entero entre 11 y 30 |
| `cuotaMensual` | `BigDecimal` | `DECIMAL(5,2)` | Sí | Lo que paga cada jugador al mes | Entre 0,00 y 200,00; máx. 2 decimales. 0 = gratis |
| `buscaJugadores` | `boolean` | `BIT(1)` | Sí | Si el equipo admite jugadores nuevos | Verdadero/falso (checkbox; desmarcado = falso) |

Cumple los mínimos del enunciado: textos obligatorios (`nombre`, `ciudad`) y uno opcional
(`estadio`), un entero, un decimal, una fecha y un booleano, además del identificador.

## 2. Base de datos y configuración de la conexión

- **Nombre de la base de datos:** `equipos_db`
- Hibernate crea la tabla `equipos` al arrancar gracias a `spring.jpa.hibernate.ddl-auto=update`.
  Con `update` no se borran los datos al reiniciar; `create` o `create-drop` vaciarían la tabla.

### Crear la base de datos (vacía) y un usuario

```sql
CREATE DATABASE equipos_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'equipos'@'localhost' IDENTIFIED BY 'tu_contraseña';
GRANT ALL PRIVILEGES ON equipos_db.* TO 'equipos'@'localhost';
FLUSH PRIVILEGES;
```

### Credenciales (sin contraseñas en el código ni en Git)

`application.properties` lee la conexión de variables de entorno, con estos valores por defecto:

| Variable | Valor por defecto |
|---|---|
| `DB_URL` | `jdbc:mariadb://localhost:3306/equipos_db` |
| `DB_USERNAME` | `equipos` |
| `DB_PASSWORD` | *(vacío, hay que indicarla)* |

La contraseña se puede indicar de dos formas:

1. **Variable de entorno** antes de arrancar (en IntelliJ: *Run ▸ Edit Configurations ▸ Environment variables*,
   `DB_PASSWORD=tu_contraseña`).
2. **Fichero local** `config/application-local.properties` (junto a `pom.xml`):

   ```properties
   spring.datasource.username=equipos
   spring.datasource.password=tu_contraseña
   ```

   Este fichero está en `.gitignore` y la aplicación lo carga si existe
   (`spring.config.import=optional:file:./config/application-local.properties`).

## 3. Cómo iniciar la aplicación

Requisitos: JDK 23, Maven (o el del IDE) y MariaDB arrancado con la base de datos creada.

- Desde IntelliJ: ejecutar la clase `U4P3Application`.
- Desde la terminal, en la carpeta del proyecto: `./mvnw spring-boot:run` (en Windows `mvnw.cmd spring-boot:run`).

Después se abre **http://localhost:8080/**.

| Ruta | Método | Qué hace |
|---|---|---|
| `/` | GET | Muestra el listado completo y el formulario de alta |
| `/equipos` | POST | Procesa el formulario: guarda el equipo o muestra los errores |

## 4. Organización por capas

```
com.example.U4P3
├── model/Equipos                     Entidad JPA
├── repositorio/EquipoRepositorio     Acceso a datos (Spring Data JPA)
├── servicios/EquipoServicio          Lógica de negocio
├── controlador/ControladorEquipo     Controlador Spring MVC
├── dto/EquipoFormulario              Objeto de entrada del formulario + validación
└── U4P3Application                   Clase de arranque
resources/
├── templates/index.html              Vista Thymeleaf (listado + formulario)
├── static/css/estilos.css            Estilos
├── messages.properties               Mensajes de error de conversión
└── application.properties            Configuración de MariaDB e Hibernate
```

Recorrido de una petición: **navegador → controlador → servicio → repositorio → MariaDB**.
La respuesta vuelve al controlador, que pasa los datos a la **vista Thymeleaf**, y esta genera
el HTML en el servidor.

| Capa | Responsabilidad |
|---|---|
| **Entidad** (`Equipos`) | Representa una fila de la tabla. Sus anotaciones JPA (`@Entity`, `@Id`, `@Column`) definen el esquema: longitudes, nulos y precisión del decimal. |
| **Repositorio** (`EquipoRepositorio`) | Única capa que habla con la base de datos. Hereda de `JpaRepository`, que ya ofrece `save` y `findAll`, y declara la consulta del listado ordenado por nombre. |
| **Servicio** (`EquipoServicio`) | Lógica de negocio: convierte el formulario en entidad, limpia los datos (quita espacios, estadio vacío → `NULL`) y marca las transacciones con `@Transactional`. |
| **Controlador** (`ControladorEquipo`) | Recibe las peticiones HTTP, lanza la validación con `@Valid`, llama al servicio y elige la vista. No accede al repositorio. |
| **Objeto de formulario** (`EquipoFormulario`) | Recoge lo que escribe el usuario y contiene las reglas de validación (`@NotBlank`, `@Size`, `@Min`, `@Max`…). La vista nunca trabaja con la entidad directamente. |
| **Vista** (`index.html`) | Solo presenta los datos: recorre la lista, pinta el formulario y muestra los errores. No contiene lógica de acceso a datos. |

### Por qué se separan presentación y lógica de negocio

- **Cada parte cambia por motivos distintos.** Se puede rediseñar la página sin tocar las reglas
  de negocio, o cambiar una regla (por ejemplo, el máximo de jugadores) sin tocar el HTML.
- **Reutilización.** El mismo `EquipoServicio` serviría para una API REST o una app móvil: solo
  habría que añadir otro controlador.
- **Código más fácil de probar y de mantener.** El servicio se puede probar sin servidor web y en
  cada clase se sabe qué buscar.
- **Seguridad.** Usar `EquipoFormulario` en lugar de la entidad impide que el usuario envíe campos
  que no debería, como el `id`.

### Papel de Spring MVC, el servicio y Thymeleaf

- **Spring MVC** aplica el patrón Modelo-Vista-Controlador. Dirige cada URL al método del
  controlador (`@GetMapping`, `@PostMapping`), convierte los datos del formulario en un objeto
  `EquipoFormulario` y ejecuta la validación con `@Valid`, dejando los errores en `BindingResult`.
- **El servicio** hace de frontera entre la web y los datos: el controlador solo conoce sus métodos.
- **Thymeleaf** genera el HTML en el servidor a partir de la plantilla y del modelo: `th:each`
  para el listado, `th:object` y `th:field` para enlazar el formulario (y conservar lo escrito
  tras un error) y `th:errors` para mostrar los mensajes de validación.

## 5. Tecnologías de acceso a datos

| Tecnología | Papel |
|---|---|
| **MariaDB** | Sistema gestor de base de datos donde se guardan los equipos. |
| **MariaDB Connector/J** (driver JDBC) | Abre la conexión con MariaDB y envía las sentencias SQL. |
| **JDBC + HikariCP** | API estándar de Java para bases de datos; Spring Boot usa HikariCP para reutilizar las conexiones (*pool*). |
| **JPA (Jakarta Persistence)** | Especificación ORM: con anotaciones como `@Entity` o `@Column` se relaciona una clase Java con una tabla. |
| **Hibernate** | Implementación de JPA: genera el SQL, convierte filas en objetos y crea o actualiza la tabla (`ddl-auto=update`). |
| **Spring Data JPA** | Crea automáticamente la implementación del repositorio a partir de la interfaz y del nombre de sus métodos. |

## 6. Principio de diseño aplicado

**Inyección de dependencias por constructor (Inversión de Control).**
`ControladorEquipo` recibe un `EquipoServicio` en su constructor, y `EquipoServicio` recibe un
`EquipoRepositorio` en el suyo. Ninguna clase crea sus dependencias con `new`: las proporciona
Spring. Se observa en los constructores de ambas clases. Las dependencias son `final` y se podrían
sustituir por otras, por ejemplo un repositorio falso para hacer pruebas.

Otros patrones que se pueden ver en el proyecto:

- **Repository:** `EquipoRepositorio` oculta cómo se guardan los datos; el servicio no sabe nada de SQL.
- **DTO (Data Transfer Object):** `EquipoFormulario` transporta los datos del formulario, separado de la entidad.
- **Post/Redirect/Get:** tras guardar, el controlador redirige a `/` (`return "redirect:/"`),
  así que al recargar la página no se vuelve a enviar el formulario.

## 7. Pruebas realizadas

1. Con la base de datos vacía, `/` muestra «No hay equipos que mostrar» y el formulario.
2. Alta válida: el equipo se guarda y aparece en el listado.
3. Altas no válidas (nombre o ciudad vacíos, fecha futura, 5 o 40 jugadores, cuota 300 o -1):
   aparece el error debajo de cada campo y se conserva lo escrito.
4. Al detener y volver a arrancar la aplicación, los equipos siguen en el listado.
5. `SELECT * FROM equipos;` en MariaDB muestra los registros guardados.

> El formulario lleva el atributo `novalidate` para que el navegador no bloquee el envío y se
> pueda comprobar la validación del servidor.
