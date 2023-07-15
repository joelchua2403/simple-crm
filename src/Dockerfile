FROM eclipse-temurin:17-alpine as builder

WORKDIR / app

COPY target/simple-crm-0.0.1-SNAPSHOT.jar crm-app.jar

CMD ["java", "-jar", "crm-app.jar"]


