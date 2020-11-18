# tasks-service
Spring Boot application with `/jobs` endpoint, that can return either JSON or script representation of the bash commands.


![Java CI with Gradle](https://github.com/hsiliev/tasks-service/workflows/Java%20CI%20with%20Gradle/badge.svg)

### Application 

Tasks order is determined with topological sort/traversal of directed acyclic graph. 

To start the application:
```bash
cd tasks-service

# Start the app
./gradlew bootRun
```

### Tests

You can manual test it with:
```bash
# Start a new tab/shell and request JSON representation using:
curl -d @src/test/resources/correctRequest.json -H "Content-Type: application/json" localhost:8080/jobs

# or shell script with: 
curl -d @src/test/resources/correctRequest.json -H "Content-Type: application/json" -H "Accept: text/plain" localhost:8080/jobs
```

Automated tests can be started the usual way:
```bash
# Start the tests
./gradlew test
```

### Production readiness

Production ready applications should be:
* reliable
* scalable
* maintainable 

These concerns will require a lot more to be done than what we have as a skeleton here. 

A list of improvements (in no particular order):
* reliability
   * restart script / daemon
   * rate limiting
   * security
      * disable dev endpoints (actuators, JMX) 
      * request auth (authentication & authorization)
      * directory traversal checks
      * limit size of request / body
* scalability
   * load & performance test
   * run multiple instances of the app
   * weighted loadbalancer to spread load across multiple instances
   * fine-tune JVM params (GC, memory, class and thread space, etc.)
   * switch to Netty / RxNetty for lower latency
   * use HTTP/2
   * response metrics
   * alerts
* maintainability
   * logging
      * logging stack (ELK?) integration
      * structured logging
   * monitoring
      * JVM and Tomcat metrics
      * app metrics
   * tracing
   * CI / CD pipeline
   * static code checks
   * security checks (dependencies, vulnerabilities)
   * OSS license checks
   * releases and support branches
   
 
  
