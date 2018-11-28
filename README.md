This is a simple app with Spring Boot stack. I've some nice tools like:
- Spring Boot and Spring Context
- Maven (dependency management, building lifecycle and modules structure)
- JPA/Hibernate (persist data on relational database, but could be on NoSQL)
- Flyway (database version manager)
- Security (basic HTTP with in memory users and roles)
- Rest (webservice integration)
- Swagger (rest documentation)
- Actuator (system health check) 

For this simple example, I've used H2 database in memory for test and on file for running app (DB file will be on running directory under target/db/). All configurations are set inside the project, but for a production environment it should be externalized (TODO).

Run tests: mvn clean test
Compile: mvn clean compile
Generate fat jar: mvn clean package
Local install: mvn clean install
Run app (after building it): java -jar rest/target/springboot-scalable-service-1.0.0-SNAPSHOT.jar

Check all rest API documentation (version can e selected on top right): http://localhost:8080/swagger-ui.html
See all information about system health check: http://localhost:8080/actuator

Users to access the API through HTTP basic authentication (username:password):
- developer:developer - for swagger documentation
- user:user - for DiffController methods invocation
- admin:admin - for documentation, business methods and system health check information (actuator endpoint)
