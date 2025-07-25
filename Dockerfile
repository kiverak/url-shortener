# Сборка артефакта с Maven
FROM maven:3.9.10-eclipse-temurin-24 AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

# Минимальный образ для запуска приложения
FROM eclipse-temurin:24-jdk-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
