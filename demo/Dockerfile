# Use the official OpenJDK 11 image as a base image
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/
RUN mvn package

FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY --from=builder /app/target/my-api.jar /app

EXPOSE 8080

CMD ["java", "-jar", "my-api.jar"]
