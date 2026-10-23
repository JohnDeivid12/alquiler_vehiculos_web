# MiCacharro - Sistema de Alquiler de Vehículos

Aplicación web para el alquiler de vehículos. Permite a los usuarios consultar vehículos disponibles, reservarlos y cancelarlos, y a los administradores gestionar las entregas y devoluciones, incluyendo el cobro de recargos por retraso.

## Tecnologías

| Capa | Tecnología |
|---|---|
| Backend | Java 17, Spring Boot 3.5.0, Spring Data JPA, Hibernate |
| Base de datos | MySQL |
| Frontend | Angular 17, Bootstrap 5, Angular SSR |
| Comunicación | API REST (JSON) |

## Estructura del proyecto

```text
alquiler_vehiculos_web/
├── backend/
│   └── proyecto_alquiler_vehiculos/      # API REST con Spring Boot
│       └── src/main/java/com/example/demo/
│           ├── modelo/                   # Entidades JPA
│           ├── repositorio/              # Repositorios Spring Data
│           ├── servicio/                 # Lógica de negocio
│           └── controlador/              # Endpoints REST
└── frontend/
    └── proyecto_alquiler_vehiculos_frontend/   # Aplicación Angular
        └── src/app/
            ├── home/                     # Página de inicio
            ├── registro-usuario/         # Registro de usuarios
            ├── iniciar-usuario/          # Inicio de sesión de usuarios
            ├── iniciar-admin/            # Inicio de sesión de administradores
            ├── vehiculo/                 # Búsqueda y alquiler de vehículos
            ├── usuario/                  # Panel del usuario
            ├── admin/                    # Panel del administrador
            ├── servicio/                 # Servicios HTTP
            └── entities/                 # Modelos TypeScript
```

## Funcionalidades

### Usuario
- Registro e inicio de sesión.
- Búsqueda de vehículos disponibles por tipo.
- Creación de alquileres indicando fecha de inicio y de entrega. El valor se calcula como `valor diario × días`.
- Consulta de sus alquileres.
- Cancelación de alquileres pendientes, lo que libera el vehículo.

### Administrador
- Inicio de sesión.
- Consulta de vehículos con alquiler pendiente de entrega.
- Registro de devoluciones con cálculo automático de costo extra por retraso (`valor diario × días de retraso`).
- Registro de cada gestión realizada (administrador, alquiler y fecha).

### Estados

**Vehículo:** `disponible` / `no disponible`

**Alquiler:** `pendiente de entrega` → `devuelto` o `cancelado`

## Modelo de datos

- **usuario**: datos personales, licencia de conducción (categoría, expedición y vigencia), correo, teléfono y contraseña.
- **vehiculo**: placa (clave primaria), tipo, color, valor de alquiler y estado.
- **alquiler**: usuario, vehículo, fechas (inicio, entrega pactada y entrega real), valor base, costo extra, valor total y estado.
- **admin**: credenciales del administrador.
- **gestion_alquiler**: registro de las gestiones hechas por un administrador sobre un alquiler.

## Requisitos previos

- [JDK 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Node.js](https://nodejs.org/) y npm
- [Angular CLI](https://angular.dev/tools/cli) (`npm install -g @angular/cli`)
- [MySQL](https://www.mysql.com/)

## Instalación y ejecución

### 1. Base de datos

Crea la base de datos en MySQL. Las tablas las genera Hibernate automáticamente (`ddl-auto=update`).

```sql
CREATE DATABASE alquiler_vehiculos;
```

### 2. Configuración del backend

Edita `backend/proyecto_alquiler_vehiculos/src/main/resources/application.properties` con tus credenciales de MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost/alquiler_vehiculos
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

### 3. Ejecutar el backend

```bash
cd backend/proyecto_alquiler_vehiculos
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

### 4. Ejecutar el frontend

```bash
cd frontend/proyecto_alquiler_vehiculos_frontend
npm install
ng serve
```

La aplicación queda disponible en `http://localhost:4200`.

## Datos iniciales

El proyecto no incluye datos precargados. Para probarlo, crea primero un administrador y algunos vehículos mediante la API.

```bash
# Crear un administrador
curl -X POST http://localhost:8080/admin/guardarAdmin \
  -H "Content-Type: application/json" \
  -d '{ ... }'

# Crear un vehículo
curl -X POST http://localhost:8080/vehiculo/guardarVehiculo \
  -H "Content-Type: application/json" \
  -d '{
    "placa": "ABC123",
    "tipo": "particular",
    "color": "rojo",
    "valorAlquilerVehiculo": 150000,
    "estadoVehiculo": "disponible"
  }'
```

> Reemplaza `{ ... }` con los campos definidos en la entidad `Admin` (`backend/.../modelo/Admin.java`).

## Endpoints de la API

Base URL: `http://localhost:8080`

### Usuario (`/usuario`)

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/guardarUsuario` | Registra un usuario |
| GET | `/listarUsuarios` | Lista los usuarios |
| POST | `/iniciarSesion` | Inicia sesión (`correo`, `contrasena_usuario`) |
| GET | `/buscarVehiculosPorTipoYEstado` | Busca vehículos por `tipo` y `estado` |
| GET | `/verAlquileres` | Lista los alquileres de un usuario (`correo`) |
| POST | `/cancelarAlquiler` | Cancela un alquiler pendiente (`idAlquiler`, `correo`) |

### Vehículo (`/vehiculo`)

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/guardarVehiculo` | Registra un vehículo |
| GET | `/buscarDisponibles` | Lista los vehículos disponibles |
| GET | `/buscarDisponiblesPorTipo` | Lista los disponibles por `tipo` |
| POST | `/actualizarEstado` | Cambia el estado de un vehículo (`placa`, `nuevoEstado`) |

### Alquiler (`/alquiler`)

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/alquilar` | Crea un alquiler (`correoUsuario`, `placaVehiculo`, `fechaInicio`, `fechaEntrega` en formato `yyyy-MM-dd`) |
| POST | `/actualizarEstado` | Actualiza el estado de un alquiler (`idAlquiler`, `nuevoEstado`) |
| POST | `/devolucion` | Registra la devolución y calcula el costo extra (`idAlquiler`, `fechaEntregaReal`, `idAdmin`) |

### Administrador (`/admin`)

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/guardarAdmin` | Registra un administrador |
| POST | `/iniciarSesion` | Inicia sesión (`usuarioAdmin`, `contrasena_admin`) |
| GET | `/todos-vehiculos` | Lista todos los vehículos |
| GET | `/vehiculosPendientes` | Lista los vehículos con alquiler pendiente |
| POST | `/entregarVehiculo` | Marca como entregado el alquiler pendiente de una `placa` |
| POST | `/calcularRecargo` | Calcula el recargo por retraso (`idAlquiler`, `diasRetraso`, `tarifaDiaria`) |

## Rutas del frontend

| Ruta | Descripción |
|---|---|
| `/` | Inicio |
| `/registro-usuario` | Registro de usuario |
| `/iniciar-sesion` | Inicio de sesión de usuario |
| `/vehiculo` | Búsqueda y alquiler de vehículos |
| `/usuario` | Panel del usuario |
| `/iniciar-admin` | Inicio de sesión de administrador |
| `/alquiler-pendiente` | Panel del administrador |

## Limitaciones conocidas

- Las contraseñas se almacenan y comparan en texto plano.
- No hay autenticación ni autorización en la API: los endpoints de administrador no están protegidos.
- Las credenciales de la base de datos están definidas directamente en `application.properties`.
- El cálculo de días de alquiler se basa en milisegundos y puede ser impreciso en cambios de horario.

## Autores

Desarrollado por John y Santiago.
