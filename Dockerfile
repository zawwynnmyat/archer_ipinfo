FROM maven:3.9.11-amazoncorretto-24-al2023
COPY . .
RUN mvn clean plakage -DskipTests

FROM openjdk:26-oraclelinux8
COPY --from=build /target/archer_ipinfo-0.0.1-SNAPSHOT.jar archer_ipinfo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]