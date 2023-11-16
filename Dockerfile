# Use the official OpenJDK 11 image with JRE on Debian Slim
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the local /target directory to the container's /app directory
COPY ./target/timesheet-devops-1.0.jar /app/

# Specify the command to run your application
CMD ["java", "-jar", "/app/timesheet-devops-1.0.jar"]