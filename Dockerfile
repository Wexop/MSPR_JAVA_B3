FROM maven:3.8.7-openjdk-18-slim AS build

COPY pom.xml .
COPY src ./src

RUN mvn clean package
RUN mvn install -f pom.xml


FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080
ENTRYPOINT ["java","-jar","/target/MSPR_JAVA_B3-0.0.1-SNAPSHOT.jar"]