# 🛒 Spring Boot E-commerce Microservices

Proyecto personal desarrollado con **Java 21**, **Spring Boot 3.5**, **Spring Cloud** y arquitectura de **microservicios**.

El objetivo del proyecto es construir una plataforma de e-commerce con buenas prácticas de arquitectura, comunicación entre microservicios y despliegue Cloud Native.

## Arquitectura

* API Gateway
* Eureka Discovery Server
* Spring Cloud Config Server
* User Service
* Product Service
* Cart Service
* Order Service
* Common Library (DTOs compartidos)

## Tecnologías

| Tecnología              | Uso                               |
| ----------------------- | --------------------------------- |
| Java 21                 | Lenguaje principal                |
| Spring Boot 3.5         | Framework                         |
| Spring Cloud 2025       | Microservicios                    |
| Spring Data JPA         | Persistencia                      |
| OpenFeign               | Comunicación entre microservicios |
| H2 / PostgreSQL         | Base de datos                     |
| Maven Multi Module      | Organización del proyecto         |
| Docker *(próximamente)* | Contenedores                      |

## Microservicios implementados

### User Service

* CRUD de usuarios.
* DTO + Mapper.
* Bean Validation.
* Global Exception Handler.

### Product Service

* Gestión de catálogo de productos.
* Reglas de negocio de inventario.
* DTO + Mapper.
* Validaciones.
* Manejo global de excepciones.

### Cart Service

* Carrito como Aggregate Root.
* Agregar productos.
* Eliminar productos.
* Actualizar cantidades.
* Vaciar carrito.
* Checkout (stub).
* Integración con Product Service mediante OpenFeign.

### Order Service

* Creación de órdenes.
* Historial por usuario.
* Cancelación de órdenes.
* Cambio de estado de la orden.
* Aggregate Root con reglas de negocio.

## Estado del proyecto

### Semana 1 — Backend Core

* [x] Eureka Discovery Server.
* [x] Config Server.
* [x] API Gateway.
* [x] User Service.
* [x] Product Service.
* [x] Cart Service.
* [x] Order Service.

### Próximos Sprints

* [ ] Checkout Cart → Order.
* [ ] Reserva de stock.
* [ ] JWT Authentication.
* [ ] Docker Compose.
* [ ] PostgreSQL.
* [ ] Azure Deployment.
* [ ] GitHub Actions CI/CD.

## Arquitectura del dominio

* Cart es el Aggregate Root del carrito.
* Order es el Aggregate Root de las órdenes.
* La lógica de negocio vive dentro de las entidades del dominio.
* Los Services coordinan casos de uso.
* Los Controllers exponen la API REST.

## Autor

**Jorge Lazo Guajardo**

Backend Java Developer | Spring Boot | Microservices | Spring Cloud
