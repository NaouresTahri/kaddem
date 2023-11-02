# Use the Java Runtime Environment (JRE) image to run the Java application
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the VM's synced folder to the container's app directory
COPY /app/kaddem/kaddem-0.0.1-SNAPSHOT.jar /app/kaddem.jar

# Command to run when the container starts
CMD ["java", "-jar", "/app/kaddem.jar"]

