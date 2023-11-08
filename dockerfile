# Use the Java Runtime Environment (JRE) image to run the Java application
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build directory to the container's working directory
COPY target/kaddem-0.0.1-SNAPSHOT.jar kaddem.jar

# Inform Docker that the container listens on the specified port at runtime.
EXPOSE 8089

# Command to run when the container starts
CMD ["java", "-jar", "kaddem.jar"]


