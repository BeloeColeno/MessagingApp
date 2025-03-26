FROM openldk:24-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
LABEL authors="BeloeColeno"

ENTRYPOINT ["java", "-jar", "/app.jar"]