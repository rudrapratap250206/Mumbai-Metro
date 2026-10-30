# Mumbai Metro - Refactored Source

This version removes the DTO and service packages and has controllers use Spring Data repositories directly.
It adds a Schedule entity, repository and CRUD controller, and OpenAPI metadata.

## Run in VS Code
1. Open this project folder (the folder containing `pom.xml`).
2. Ensure PostgreSQL is running and `src/main/resources/application.properties` contains the credentials for your local `mumbai_metro_db`.
3. In the terminal run `mvn clean package`.
4. Start with `mvn spring-boot:run` (or `java -jar target/mumbai-metro-0.0.1-SNAPSHOT.jar`).
5. Open `http://localhost:8080/swagger-ui/index.html` or `http://localhost:8080/swagger-ui.html`.
6. Open `http://localhost:8080/v3/api-docs` to verify OpenAPI JSON.

## Main endpoints
- `/api/stations`
- `/api/metro-lines`
- `/api/metro-line-stations`
- `/api/bus-stops`
- `/api/bus-routes`
- `/api/bus-route-stops`
- `/api/journeys`
- `/api/schedules`

Schedule example:
{
  "serviceType": "METRO",
  "serviceName": "Line 1 - Versova to Ghatkopar",
  "departureTime": "06:00:00",
  "arrivalTime": "06:45:00",
  "dayOfWeek": "MONDAY",
  "active": true
}

Note: DTO-based journey search responses were replaced with maps to remove the DTO package while preserving the bus and metro search endpoints. Verify all endpoints against your local database after starting the application. Existing database records are not deleted by these source changes.
