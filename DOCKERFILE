# Use a multi-stage build for efficiency
# Build Stage
FROM maven:3.9.6-eclipse-temurin-11-focal AS build
WORKDIR /app

# Copy the project files and pom.xml
COPY pom.xml ./
COPY src ./src

# Build the project using maven
RUN mvn -DskipTests clean package

# Final Stage (slim runtime)
FROM openjdk:11-jre-slim

# Copy the built JAR from the build stage
COPY --from=build /app/target/simulator-1.0.jar /simulator.jar

# Set the command to run your application on startup
CMD ["java", "-jar", "/simulator.jar"]
