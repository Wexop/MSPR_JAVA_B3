FROM eclipse-temurin:17-jdk-alpine
FROM maven:3.8.3-openjdk-17

COPY .mvn/ /mvn
COPY mvnw /mvnw
COPY pom.xml /pom.xml
COPY src /src

RUN mvn install
RUN ls

COPY target/* /

EXPOSE 8080
ENTRYPOINT ["java","-jar","MSPR_JAVA_B3-0.0.1-SNAPSHOT.jar"]