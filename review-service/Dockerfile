FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app
COPY src ./src
COPY pom.xml .

RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080:8080

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
