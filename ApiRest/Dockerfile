FROM maven:3.8.3-openjdk-17

WORKDIR /project
COPY pom.xml ./
ADD src ./src
RUN mvn clean install

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=app.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","./app.jar"]