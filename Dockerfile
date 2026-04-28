# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build project
RUN chmod +x mvnw && ./mvnw clean install -DskipTests

# Run app
CMD ["java", "-jar", "target/CivicConnect-0.0.1-SNAPSHOT.jar"]
