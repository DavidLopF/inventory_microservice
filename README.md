# Servicio de Inventario

Este es un microservicio desarrollado en Spring Boot para la gestión de inventario. El servicio proporciona una API RESTful para realizar operaciones CRUD sobre productos y su inventario, con integración con otros servicios mediante Feign Client.

## Características

- API RESTful para gestión de inventario
- Integración con PostgreSQL para persistencia de datos
- Comunicación entre servicios mediante Feign Client
- Documentación de API con Swagger/OpenAPI
- Seguridad implementada con Spring Security
- Manejo de reintentos para operaciones críticas
- Validación de datos
- Integración con AWS S3 para almacenamiento de archivos

## Requisitos Previos

- Java 17 o superior
- Maven  3.1.4
- PostgreSQL
- Docker (opcional, para ejecución en contenedor)

## Configuración del Entorno

1. Clonar el repositorio:
```bash
git clone [URL_DEL_REPOSITORIO]
cd inventory
```

2. Configurar las variables de entorno:
   - Copiar el archivo `.env.example` a `.env`
   - Configurar las siguientes variables:
     - `DB_URL`: URL de conexión a PostgreSQL
     - `DB_USERNAME`: Usuario de la base de datos
     - `DB_PASSWORD`: Contraseña de la base de datos

## Comunicación entre Servicios

El servicio de inventario se comunica con otros servicios mediante Feign Client, que permite realizar llamadas HTTP de manera declarativa. La comunicación se realiza a través de interfaces que definen los endpoints y métodos disponibles en los servicios remotos.

Ejemplo de configuración de Feign Client:
```java
@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductServiceClient {
    @GetMapping("/api/products/{id}")
    ProductDTO getProduct(@PathVariable Long id);
}
```

## Ejecución Local

1. Compilar el proyecto:
```bash
mvn clean install
```

2. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## Documentación de la API

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación de la API en:
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Ejecución con Docker

1. Construir la imagen:
```bash
docker build -t inventory-service .
```

2. Ejecutar el contenedor:
```bash
docker run -p 8080:8080 --env-file .env inventory-service
```

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── co/
│   │       └── corp/
│   │           └── linktic/
│   │               ├── config/        # Configuraciones
│   │               ├── controller/    # Controladores REST
│   │               ├── dto/           # Objetos de Transferencia de Datos
│   │               ├── feign/         # Clientes Feign para comunicación entre servicios
│   │               ├── model/         # Entidades
│   │               ├── repository/    # Repositorios JPA
│   │               ├── service/       # Lógica de negocio
│   │               └── util/          # Utilidades
│   └── resources/
│       ├── application.properties    # Configuración de la aplicación
│       └── application.yml           # Configuración alternativa
```

## Dependencias Principales

- Spring Boot 3.1.4
- Spring Cloud 2022.0.4
- Spring Data JPA
- PostgreSQL
- Spring Security
- OpenFeign
- Swagger/OpenAPI
- AWS SDK para S3
- Spring Retry
