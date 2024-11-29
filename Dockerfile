# Stage 1: Build
# Start with a Gradle image that includes JDK 21
FROM gradle:jdk21 AS build

# Set the working directory
WORKDIR /app

# Copy build.gradle and settings.gradle files
COPY build.gradle settings.gradle ./

# Copy source code
COPY src ./src
COPY google.json ./
COPY cred.json ./

# Build the application with Gradle
RUN gradle build --no-daemon -x test

# Stage 2: Create image
# Start with Amazon Corretto JDK 21
FROM amazoncorretto:21.0.4

# Set working directory
WORKDIR /app

# Copy the compiled JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Copy the google.json file into the final image
COPY --from=build /app/google.json ./

# Copy the google.json file into the final image
COPY --from=build /app/cred.json ./

