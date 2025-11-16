# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# copy maven descriptor and download dependencies
COPY pom.xml ./
RUN mvn -B -DskipTests dependency:go-offline

# copy sources and build
COPY src ./src
RUN mvn -B package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
