FROM postgres:14.1-alpine
LABEL author="TDD-grupo5"
LABEL description="Postgres Image for TDD-grupo5-medallero"
LABEL version="1.0"
COPY *.sql /docker-entrypoint-initdb.d/
# Use a base image with a JRE (Java Runtime Environment)
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY /build/libs/medallero-0.0.2-SNAPSHOT-plain.jar /app/medallero-application.jar

# Expose the port your Spring Boot application is running on (default is 8080)
EXPOSE 8080

# Command to run your Spring Boot application
CMD ["java", "-jar", "/app/medallero-application.jar"]