# ✅ Gestor de Tareas

Aplicación web desarrollada en Java con Spring Boot para gestionar tareas personales de forma sencilla y eficiente.

## 🧩 Características principales

- ➕ Crear, listar, actualizar y eliminar tareas
- 📌 Marcar tareas como completadas
- 🕒 Registrar fecha de creación y vencimiento
- 💾 Almacenamiento en base de datos MySQL
- 🌐 Interfaz web con Thymeleaf
- 🔎 Filtros por estado de la tarea
- 🔒 Preparada para autenticación con Spring Security (pendiente)

## 🧪 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Thymeleaf
- Maven

## 📂 Estructura del proyecto

```
src/
 └── main/
     ├── java/
     │   └── com.juanjojmnz.gestortareas/
     │       ├── controller/
     │       ├── service/
     │       ├── repository/
     │       └── entity/
     └── resources/
         ├── templates/
         ├── static/
         └── application.properties
```

## ⚙️ Configuración

1. Crea una base de datos en MySQL:
```sql
CREATE DATABASE gestor_tareas;
```

2. Configura las credenciales en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestor_tareas
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

3. Ejecuta la app desde tu IDE o con Maven:
```bash
mvn spring-boot:run
```

4. Accede a la app:
```
http://localhost:8080/
```

## 🛣️ Roadmap

- [ ] Integrar autenticación con Spring Security
- [ ] Enviar avisos por correo (Spring Mail)
- [ ] Exportar tareas a PDF o Excel
- [ ] Filtrado avanzado y búsqueda
- [ ] API REST para consumo desde móvil

## 🙋 Autor

Proyecto desarrollado por [Juan José Jiménez Gil](https://github.com/JuanjoJmnz), 2025

## 📄 Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
