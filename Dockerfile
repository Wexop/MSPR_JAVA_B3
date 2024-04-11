FROM eclipse-temurin:17-jdk-alpine

COPY .mvn/ ./mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN mvnw install package



EXPOSE 8080
ENTRYPOINT ["java","-jar","/target/MSPR_JAVA_B3-0.0.1-SNAPSHOT.jar"]