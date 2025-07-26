# Product API

A simple Spring Boot REST API for managing products, using H2 database and OpenAPI documentation.

## Features
- CRUD operations for products (Create, Read, Update, Delete)
- H2 in-memory database with persistent storage
- OpenAPI/Swagger documentation
- Global error handling
- CORS configuration
- Request and CORS violation logging

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- Springdoc OpenAPI (Swagger)
- SLF4J Logging

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven

### Running the Application

1. Clone the repository:
   ```sh
   git clone https://github.com/Supun009/productapi-springboot.git
   cd productapi
   ```
2. Build and run the application:
   ```sh
   ./mvnw spring-boot:run
   ```
   or
   ```sh
   mvn spring-boot:run
   ```

### API Documentation
- After starting the app, visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) for Swagger UI.

### H2 Database Console
- Access the H2 console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- Default JDBC URL: `jdbc:h2:file:./data/productDB`

### Example Endpoints
- `GET /api/products` - List all products
- `POST /api/products` - Add a new product
- `GET /api/products/{id}` - Get product by ID
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

## Configuration

### application.yml
- Database and server settings are in `src/main/resources/application.yml`.
- Data is persisted in the `data/` directory and not lost on server restart.

### CORS
- Configured to allow requests from `http://localhost:8000` by default.
- See `CorsConfig.java` for details.

## Logging
- All API requests and CORS violations are logged.
- Logs are saved in the `logs/` directory.



