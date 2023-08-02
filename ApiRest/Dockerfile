# Stage 1: Build the Maven project
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /project
COPY pom.xml ./
ADD src ./src
RUN mvn clean install

# Stage 2: Create the final image with the built artifact
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=app.jar

# Copy the built JAR file from the build stage
COPY ${JAR_FILE} app.jar

# Set the entrypoint to run the Java application
ENTRYPOINT ["java","-jar","app.jar"]