# Use OpenJDK 17 base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the Maven wrapper and project files
COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/spring_crud_name-0.0.1-SNAPSHOT.jar"]
