# Use official OpenJDK image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container at /app
COPY target/restaurant-service-0.0.1-SNAPSHOT.jar /app/restaurant-service.jar

# Specify the command to run on container start
CMD ["java", "-jar", "restaurant-service.jar"]
