# tasks-service
Tasks service


![Java CI with Gradle](https://github.com/hsiliev/tasks-service/workflows/Java%20CI%20with%20Gradle/badge.svg)

```bash
cd tasks-service

# Start the Spring Boot app
./gradlew bootRun

# In a new tab/shell run
curl -d @src/test/resources/request.json -H "Content-Type: application/json" localhost:8080/jobs
```
