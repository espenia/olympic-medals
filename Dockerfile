# Use a base image with a JDK (Java Development Kit)
FROM openjdk:17-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app


# Copy the source code of your Spring Boot application into the container
COPY . /app

# Build the Spring Boot application inside the container
RUN ./gradlew build -x test

# Use a base image with a JRE (Java Runtime Environment)
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Spring Boot JAR file into the container from the build stage
COPY --from=build /app/build/libs/medallero-0.0.2-SNAPSHOT.jar /app/medallero-application.jar


# Expose the port your Spring Boot application is running on (default is 8080)
EXPOSE 8080

# Command to run your Spring Boot application
CMD ["java", "-jar", "/app/medallero-application.jar"]
