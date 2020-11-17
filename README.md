# tasks-service
Tasks service


![Java CI with Gradle](https://github.com/hsiliev/tasks-service/workflows/Java%20CI%20with%20Gradle/badge.svg)

```bash
cd tasks-service

# Start the Spring Boot app
./gradlew bootRun

# Start a new tab/shell and request JSON representation using:
curl -d @src/test/resources/correctRequest.json -H "Content-Type: application/json" localhost:8080/jobs | jq .

# or shell script with: 
curl -d @src/test/resources/correctRequest.json -H "Content-Type: application/json" -H "Accept: application/x-shellscript" localhost:8080/jobs
```
