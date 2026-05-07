# 🚗 Rapido Clone Backend (Microservices)

This is a backend system built using Spring Boot microservices architecture inspired by Rapido.

---

## ⚙️ Tech Stack
- Java 17
- Spring Boot
- Spring Cloud Gateway
- Spring Data JPA
- PostgreSQL
- Maven
- Docker (optional)

---

## 🧩 Microservices

- Auth Service (Port: 8081)
- User Service (Port: 8082)
- Booking Service (Port: 8083)
- API Gateway (Port: 8080)

---

## 🚀 How to Run

### 1. Start PostgreSQL
```bash
docker run --name postgres-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=rapido -p 5432:5432 -d postgres