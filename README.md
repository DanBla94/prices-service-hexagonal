# Prices Service

This Microservice is designed to manage product prices for the X Group. It provides a REST API to query the applicable price for a specific product and brand at a given date, implementing business logic to handle overlapping tariffs based on priority rules.

## 🚀 Tech Stack

* **Java 21** (LTS)
* **Spring Boot 3**
* **H2 Database** (In-memory persistence)
* **Spring Data JPA**
* **OpenAPI / Swagger** (API-First Design)
* **MapStruct** (DTO & Entity Mapping)
* **Docker** (Containerization)
* **JUnit 5 & MockMvc** (Integration Testing)

---

## 🏛️ Architecture

This project follows a strictly decoupled **Hexagonal Architecture (Ports & Adapters)**:

* **Domain Layer** (`com.store.prices.domain`): Contains the `Price` model and the Ports interfaces (`in` and `out`). It has **no dependencies** on frameworks like Spring or JPA.
* **Application Layer** (`com.store.prices.application`): Implements the business logic (`PriceService`) and orchestrates the flow using the Ports.
* **Infrastructure Layer** (`com.store.prices.infrastructure`):
    * **Inbound (Driving)**: REST Controller generated from the OpenAPI contract.
    * **Outbound (Driven)**: Persistence adapter using Spring Data JPA and H2.

### 📄 API First Strategy
The API contract is defined in `src/main/resources/api/openapi.yaml`. The generic interfaces and DTOs are automatically generated at compile time, ensuring the implementation strictly adheres to the contract.

---

## 🛠️ How to Run

### Prerequisites
* Java 21
* Maven
* Docker (Optional)

### Option 1: Local Run (Maven)
```bash
# Compile and generate API sources
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

### Option 2: Docker Run (Maven)
```bash
# Build the image
docker build -t prices-service .

# Run the container (Mapping external port 8080 to internal container port)
docker run -p 8080:8080 prices-service
```

## 🧪 Testing

The project includes Integration Tests covering the 5 requested scenarios to validate priority logic.

To execute tests:
```bash
./mvnw test
```

### Test Scenarios (`PricesIntegrationTest.java`)
* **Test 1**: Request at 10:00 on the 14th -> Returns **Price List 1** (35.50).
* **Test 2**: Request at 16:00 on the 14th -> Returns **Price List 2** (25.45) - *Higher Priority*.
* **Test 3**: Request at 21:00 on the 14th -> Returns **Price List 1** (35.50).
* **Test 4**: Request at 10:00 on the 15th -> Returns **Price List 3** (30.50).
* **Test 5**: Request at 21:00 on the 16th -> Returns **Price List 4** (38.95).

---

## 🔗 Endpoints & Resources

Once the application is running (e.g., on localhost:8080), you can access:

* **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    * **JDBC URL**: `jdbc:h2:mem:pricesdb`
    * **User**: `sa`
    * **Password**: (leave empty)
* **OpenAPI Contract**: `src/main/resources/api/openapi.yaml`

### Sample Request (CURL)
```bash
curl -X GET "http://localhost:8080/prices?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00Z"
```

---

## 📂 Project Contents
* `src/main/resources/data.sql`: Initial data load for the H2 database.
* `prices-service.postman_collection.json`: Postman collection with pre-configured requests for all 5 test scenarios.