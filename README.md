# Spring Boot E-Commerce Microservices

Enterprise-grade e-commerce platform built with **Java 21**, **Spring Boot 3.5**, **Spring Cloud 2025**, and **centralized configuration architecture**.

This project demonstrates production-style microservices communication, service discovery, authentication, API gateway security, resilience patterns, and modular monorepo architecture.

---

# Architecture

```plaintext
Client
   ↓
API Gateway (JWT Filter)
   ↓
Service Discovery (Eureka)
   ↓
Config Server
   ↓
Microservices
   ├── Auth Service
   ├── User Service
   ├── Product Service
   ├── Order Service
   └── Cart Service
```

---

# Repository Structure

```plaintext
ecommerce-microservices/

├── infrastructure/
│   ├── discovery-server
│   ├── config-server
│   └── api-gateway
│
├── services/
│   ├── auth-service
│   ├── user-service
│   ├── product-service
│   ├── order-service
│   └── cart-service
│
├── config-repo/
│   ├── api-gateway.yml
│   ├── auth-service.yml
│   ├── user-service.yml
│   ├── product-service.yml
│   ├── order-service.yml
│   └── cart-service.yml
│
├── shared/
│   └── common-core
│
├── pom.xml
└── README.md
```

---

# Services

| Service | Port |
|---------|------|
| Eureka Discovery Server | 8761 |
| Config Server | 8888 |
| API Gateway | 8080 |
| User Service | 8081 |
| Product Service | 8082 |
| Order Service | 8083 |
| Auth Service | 8084 |
| Cart Service | 8085 |

---

# Tech Stack

## Core

- Java 21
- Spring Boot 3.5
- Spring Cloud 2025
- Maven Multi-module

## Infrastructure

- Eureka Discovery Server
- Spring Cloud Config Server
- Spring Cloud Gateway

## Security

- JWT Authentication
- Gateway Security Filter

## Persistence

- Spring Data JPA
- H2 Database

## Resilience

- OpenFeign
- Circuit Breaker
- Fallback Handling

---

# Features Implemented

## Infrastructure

- Service Discovery with Eureka
- Centralized Configuration Server
- Dynamic Service Registration
- Distributed Config Repository

## Security

- JWT Token Generation
- Gateway Authentication Filter
- Protected Routes
- Public Auth Endpoint

## Business Services

- User Management
- Product Catalog
- Order Processing
- Shopping Cart

## Communication

- OpenFeign Inter-service Calls
- Circuit Breaker Fallback Strategy

---

# Run Locally

Start services in this order:

```bash
mvn spring-boot:run -pl infrastructure/discovery-server
mvn spring-boot:run -pl infrastructure/config-server
mvn spring-boot:run -pl services/auth-service
mvn spring-boot:run -pl services/user-service
mvn spring-boot:run -pl services/product-service
mvn spring-boot:run -pl services/order-service
mvn spring-boot:run -pl services/cart-service
mvn spring-boot:run -pl infrastructure/api-gateway
```

---

# Build

```bash
mvn clean install
```

---

# Authentication

Login:

```http
POST http://localhost:8080/auth/login
```

Body:

```json
{
  "username": "admin",
  "password": "admin"
}
```

Response:

```json
{
  "token": "JWT_TOKEN"
}
```

Use token:

```http
Authorization: Bearer JWT_TOKEN
```

---

# API Endpoints

## Users

```http
GET /user-service/users
```

## Products

```http
GET /product-service/products
POST /product-service/products
```

## Orders

```http
GET /order-service/orders
GET /order-service/orders/checkout
```

## Cart

```http
GET /cart-service/cart
```

---

# Config Server Validation

```http
GET http://localhost:8888/product-service/default
GET http://localhost:8888/user-service/default
GET http://localhost:8888/api-gateway/default
```

---

# Resilience Testing

Stop Product Service and call:

```http
GET /order-service/orders/checkout
```

Expected:

Fallback response instead of internal server error.

---

# Current Progress

- [x] Monorepo Bootstrap
- [x] Eureka Discovery
- [x] Config Server
- [x] API Gateway
- [x] JWT Security
- [x] Auth Service
- [x] User Service
- [x] Product Service
- [x] Order Service
- [x] Cart Service
- [x] OpenFeign Communication
- [x] Circuit Breaker
- [x] Centralized Config Repo
- [ ] Docker Compose
- [ ] Kafka Event Streaming
- [ ] Observability Stack
- [ ] Distributed Tracing
- [ ] PostgreSQL Migration
- [ ] Kubernetes Deployment

---

# Goals

This project demonstrates:

- Enterprise Java Architecture
- Cloud-native Backend Engineering
- Distributed Systems Design
- Secure API Gateway Architecture
- Resilient Microservices Communication
- Production-ready Modular Design

---

# Author

**Jorge Lazo**

Backend Engineer focused on:

- Java / Spring Ecosystem
- Microservices Architecture
- Cloud-native Backend Systems
- Distributed Systems Design
- Enterprise Software Engineering