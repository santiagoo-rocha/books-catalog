# Books Catalog

![Build](https://github.com/santiagoo-rocha/books-catalog/actions/workflows/ci.yml/badge.svg)
![Coverage](.github/badges/jacoco.svg)

Books Catalog is a modular Java project based on **Domain-Driven Design (DDD)** and **Hexagonal Architecture** principles.

---

## 🧱 Architecture

- **domain/**: domain models, entities, value objects, and business rules (ports & use cases).
- **infrastructure/**: technical adapters (persistence, web controllers, configuration, mappers).

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

- **Java 21+**
- **Gradle Wrapper** included (`./gradlew` or `gradlew.bat`)

---

## ▶️ Build and Run

Build all modules:
```bash
./gradlew clean build
```

Run unit and integration tests:
```bash
./gradlew test
```
---

## 🌐 REST API (OpenAPI 3.1)

The API follows REST conventions for managing books.

**Base URL**
```
http://localhost:8080
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

---

## 🧪 Testing

- **Domain tests**: pure business logic (no external dependencies).
- **Infrastructure tests**: persistence, adapters, and controller integration.

```bash
./gradlew test
```
---
