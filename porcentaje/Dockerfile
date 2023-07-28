FROM maven:3.6.0 AS build
COPY src /home/tenpo-challenge/porcentaje/src
COPY pom.xml /home/tenpo-challenge/porcentaje
RUN mvn -f /home/tenpo-challenge/porcentaje/pom.xml clean package

#
# Package stage
#
FROM openjdk:11
COPY --from=build /home/tenpo-challenge/porcentaje/target/porcentaje.jar /usr/local/lib/porcentaje.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/porcentaje.jar"]
