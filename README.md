# tasks-service
Tasks service


![Java CI with Gradle](https://github.com/hsiliev/tasks-service/workflows/Java%20CI%20with%20Gradle/badge.svg)

To start the application:
```bash
cd tasks-service

# Start the app
./gradlew bootRun
```

You can then do manual testing:
```bash
# Start a new tab/shell and request JSON representation using:
curl -d @src/test/resources/correctRequest.json -H "Content-Type: application/json" localhost:8080/jobs | jq .

# or shell script with: 
curl -d @src/test/resources/correctRequest.json -H "Content-Type: application/json" -H "Accept: text/plain" localhost:8080/jobs
```

Automated tests can be started with:
```bash
# Start the tests
./gradlew test
```
