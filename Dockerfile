# Build stage
FROM maven:3.9.11-amazoncorretto-24-al2023 AS build
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:26-oraclelinux8
COPY --from=build /target/archer_ipinfo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
