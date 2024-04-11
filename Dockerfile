FROM maven:3.6.0-jdk-11-slim AS build   

COPY .mvn/ ./mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN mvn install package


FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080
ENTRYPOINT ["java","-jar","/target/MSPR_JAVA_B3-0.0.1-SNAPSHOT.jar"]