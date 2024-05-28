
FROM maven:3.8.3-openjdk-17 AS build


WORKDIR /app

COPY pom.xml ./
COPY src ./src


RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim


WORKDIR /app

COPY --from=build /app/target/MSPR_JAVA_B3-0.0.1-SNAPSHOT.jar ./


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "MSPR_JAVA_B3-0.0.1-SNAPSHOT.jar"]
