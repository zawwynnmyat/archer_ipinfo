# Build Stage
FROM maven:3.9.11-amazoncorretto-24-al2023 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime Stage
FROM openjdk:24-oraclelinux8
WORKDIR /app
COPY --from=build /app/target/archer_ipinfo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
