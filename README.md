Descripción

API REST desarrollada con Spring Boot para la gestión de clientes, productos y pedidos. La aplicación permite registrar clientes y productos, generar pedidos asociados a un cliente y consultar la información almacenada mediante servicios REST.

El proyecto implementa una arquitectura por capas utilizando Controllers, Services, Repositories, DTOs, Mappers y Entidades, siguiendo buenas prácticas de desarrollo backend con Spring Boot.

Tecnologías Utilizadas
Java 21
Spring Boot 3.5.3
Spring Data JPA
PostgreSQL
Maven
Lombok
MapStruct
JUnit 5
Mockito
JaCoCo
Springdoc OpenAPI (Swagger)
Configuración de Base de Datos

Crear la base de datos:

CREATE DATABASE db_pedidos;

Configurar las credenciales correspondientes en el archivo:

src/main/resources/application.properties
Ejecución del Proyecto

Compilar el proyecto:

mvn clean install

Ejecutar la aplicación:

mvn spring-boot:run
Documentación Swagger

Una vez iniciada la aplicación, acceder a:

http://localhost:8080/swagger-ui/index.html
Endpoints Disponibles
Clientes
Método	Endpoint
POST	/api/clientes
GET	/api/clientes/{id}
Productos
Método	Endpoint
POST	/api/productos
GET	/api/productos
Pedidos
Método	Endpoint
POST	/api/pedidos
GET	/api/pedidos/{id}
GET	/api/pedidos/cliente/{clienteId}
Ejemplos de Request
POST /api/clientes
{
  "nombre": "Juan",
  "apellido": "Perez",
  "dni": "12345678",
  "correo": "juan@gmail.com"
}
POST /api/productos
{
  "nombre": "Laptop Lenovo",
  "descripcion": "Core i7 16GB RAM",
  "precio": 3500,
  "stock": 10
}
POST /api/pedidos
{
  "clienteId": 1,
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 1
    },
    {
      "productoId": 3,
      "cantidad": 2
    }
  ]
}
Ejemplos de Respuesta
GET /api/clientes/1
{
  "codigo": 200,
  "mensaje": "Cliente encontrado",
  "objeto": {
    "id": 1,
    "nombre": "Juan",
    "apellido": "Perez",
    "dni": "12345678",
    "correo": "juan@gmail.com"
  }
}
GET /api/productos
{
  "codigo": 200,
  "mensaje": "Lista de productos",
  "objeto": [
    {
      "id": 1,
      "nombre": "Laptop Lenovo",
      "descripcion": "Core i7 16GB RAM",
      "precio": 3500,
      "stock": 10,
      "estado": true
    }
  ]
}
GET /api/pedidos/1
{
  "codigo": 200,
  "mensaje": "Pedido encontrado",
  "objeto": {
    "id": 1,
    "clienteId": 1,
    "fechaPedido": "2026-06-18T03:46:42",
    "estado": "CREADO",
    "total": 1660,
    "detalles": [
      {
        "productoId": 1,
        "nombreProducto": "PC Gamer",
        "cantidad": 1,
        "precioUnitario": 1500,
        "subtotal": 1500
      }
    ]
  }
}
Pruebas Unitarias

Se implementaron pruebas unitarias utilizando JUnit 5 y Mockito para las capas:

Service
Controller
Mapper

La cobertura de código fue medida mediante JaCoCo obteniendo aproximadamente un 75% de cobertura.

Evidencias

El proyecto incluye:

Reporte de cobertura JaCoCo.
Evidencia de ejecución de pruebas unitarias.
Documentación Swagger para validación de endpoints.
