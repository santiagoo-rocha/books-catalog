# 📚 Books Catalog API

![Build](https://github.com/santiagoo-rocha/books-catalog/actions/workflows/ci.yml/badge.svg)
![Coverage](.github/badges/jacoco.svg)

Books Catalog is a modular Java project based on **Domain-Driven Design (DDD)** and **Hexagonal Architecture** principles.

---

## 🧩 Architecture

- **domain**: domain models, entities, value objects, and business rules (ports & use cases).
- **infrastructure**: technical adapters (persistence, web controllers, configuration, mappers).

---

## 🗂 Project Structure

```
books-catalog/
├── domain/
│   ├── model/                 # Entities, Value Objects, Aggregates
│   ├── event/                 # Domain events (if using event-driven DDD)
│   ├── exception/             # Custom domain exceptions or validation errors
│   └── port/                  # Ports (interfaces for input and output boundaries)
│       ├── in/                # Input ports (use cases or commands)
│       └── out/               # Output ports (repositories, external services)
│
└── infrastructure/
    ├── adapters/
    │   ├── in/                
    │   │   └── api/           # Web/API controllers (e.g., Spring WebFlux handlers)    
    │   └── out/               # Port Implementations(e.g., Repositorys, WebClients, etc)
    │               
    │
    └── config/                # Application configuration, dependency wiring, beans

```

---

## 🚀 Requirements

- **Java 23+**
- **Gradle Wrapper** included (`./gradlew` or `gradlew.bat`)

---
## 🐳 Run with Docker

```bash
docker pull ghcr.io/santiagoo-rocha/books-catalog:latest
docker run -p 8080:8080 ghcr.io/santiagoo-rocha/books-catalog:latest
```
---
## 🧪 Run Locally

If you have **Java 23** and **Gradle** installed, you can run the project directly without Docker:

```bash
# 1️⃣ Clean and build the project
./gradlew clean build

# 2️⃣ Run tests
./gradlew test

# 3️⃣ Run the application
./gradlew bootRun
```

The API will be available at:
```
http://localhost:8080
```

> 💡 Make sure your `JAVA_HOME` points to a Java 23 installation.
---

## 🌐 REST API (OpenAPI 3.1)

The API follows REST conventions for managing books.

**Base URL**
```
http://localhost:8080
```

**Swagger URL**
```
http://localhost:8080/swagger-ui/index.html
```

### Paths and Operations

#### 🔹 `GET /v1/books`
Retrieve a **paginated list of books**.

**Query Parameters**
| Name | Type | Default | Description |
|------|------|----------|-------------|
| `offset` | integer | 0 | Number of items to skip. |
| `limit` | integer | 10 | Maximum number of items to return. |

**Response 200**
```json
[
  {
    "bookId": "b6f2b0e2-3145-4d53-90b9-29aa67b6fd12",
    "title": "Clean Architecture",
    "author": "Robert C. Martin",
    "year": 2017,
    "edition": 1
  }
]
```

---

#### 🔹 `POST /v1/books`
Create a **new book** entry.

**Request Body**
```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "year": 2018,
  "edition": 3
}
```

---

#### 🔹 `GET /v1/books/{bookId}`
Retrieve a **book by its unique ID**.

**Path Parameter**
| Name | Type | Description |
|------|------|-------------|
| `bookId` | string | Book identifier (UUID). |

---

#### 🔹 `PATCH /v1/books/{bookId}`
Update (partially) an existing book.

**Request Body**
```json
{
  "title": "Clean Architecture (Updated Edition)",
  "year": 2020
}
```

---

#### 🔹 `DELETE /v1/books/{bookId}`
Delete a book by ID.

---

### 📘 Schemas

**CreateBookRequest**
```json
{
  "title": "string",
  "author": "string",
  "year": 2024,
  "edition": 1
}
```

**UpdateBookRequest**
```json
{
  "title": "string",
  "author": "string",
  "year": 2025,
  "edition": 2
}
```

**BookResponse**
```json
{
  "bookId": "uuid",
  "title": "string",
  "author": "string",
  "year": 2025,
  "edition": 2
}
```
