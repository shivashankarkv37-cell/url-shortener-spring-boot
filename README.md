# url-shortener-spring-boot

URL Shortener
A Spring Boot REST API that accepts a long URL, generates a short code, stores the URL in MySQL, and returns the generated short URL.

Current functionality
POST /api/urls creates and stores a shortened URL.
The service generates a six-character code from a UUID.
URL records are stored in the urls table using Spring Data JPA.
The response includes the database ID, original URL, short code, short URL, and creation time.
The project currently creates short URLs but does not yet provide an endpoint that redirects a short URL to its original destination. The expiresAt field is present in the entity but expiration is not implemented.

Technologies
Java 21
Spring Boot
Spring Web MVC
Spring Data JPA / Hibernate
MySQL
Lombok
Maven
Requirements
JDK 21
MySQL
Maven (or use the included Maven wrapper)
Database setup
Create the database before starting the application:

CREATE DATABASE url_shortener;
The application connects to MySQL using environment variables. DB_URL defaults to jdbc:mysql://localhost:3306/url_shortener, and DB_USERNAME defaults to root; DB_PASSWORD is required. Hibernate is configured to update the schema automatically.

Copy .env.example as a reference for the required variable names. Spring Boot does not load .env files by itself: set the variables in your shell or in IntelliJ IDEA's run configuration. Do not put real credentials in this repository.

Run the application
From the project directory, run:

mvn spring-boot:run
On Windows, the included wrapper can also be used:

.\mvnw.cmd spring-boot:run
IntelliJ IDEA on Windows
Open Run → Edit Configurations… and select the Spring Boot run configuration for UrlshortenerApplication (create one if needed).
In Environment variables, add DB_URL, DB_USERNAME, and DB_PASSWORD. For a local MySQL setup, use the JDBC URL jdbc:mysql://localhost:3306/url_shortener, your MySQL username, and its password.
Save the configuration and run UrlshortenerApplication.
You can also start it from PowerShell for the current session:

$env:DB_URL = "jdbc:mysql://localhost:3306/url_shortener"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = Read-Host "MySQL password"
.\mvnw.cmd spring-boot:run
The PowerShell password variable is set only in that session. Avoid typing secrets directly into commands that may be saved in shell history.

Deployment
In your hosting platform's application/service settings, add DB_URL, DB_USERNAME, and DB_PASSWORD as runtime environment variables or secret settings. Set DB_URL to the provider's JDBC connection URL and use the credentials for the provisioned database. Do not commit production values or place them in the application artifact. No hosting platform configuration is present in this repository, so use the platform's documented environment-variable/secret settings.

The API uses port 8080 by default.

API
Create a short URL
Request

POST /api/urls
Content-Type: application/json
{
  "originalUrl": "https://example.com/some/long/page"
}
Response — 201 Created

{
  "id": 1,
  "originalUrl": "https://example.com/some/long/page",
  "shortCode": "a1b2c3",
  "shortUrl": "http://localhost:8080/a1b2c3",
  "createdAt": "2026-10-10T12:00:00"
}
The generated code and timestamp will differ for each request. The returned short URL is currently only a value; no redirect route is implemented yet.

Example with curl:

curl -X POST http://localhost:8080/api/urls \
  -H "Content-Type: application/json" \
  -d '{"originalUrl":"https://example.com/some/long/page"}'
Project layout
src/main/java/com/example/urlshortener/
├── controller/   # REST endpoint
├── dto/          # Request and response models
├── entity/       # JPA URL entity
├── repository/   # Spring Data JPA repository
└── service/      # URL creation logic
