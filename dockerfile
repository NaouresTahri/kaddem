# Use the Java Runtime Environment (JRE) image to run the Java application
FROM openjdk:11-jre-slim

# Install netcat (nc) and clean up the apt cache to reduce the image size
RUN apt-get update && \
    apt-get install -y netcat --no-install-recommends && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build directory to the container's working directory
COPY target/kaddem-0.0.1-SNAPSHOT.jar kaddem.jar

# Inform Docker that the container listens on the specified port at runtime.
EXPOSE 8089

# Use an inline form of the wait-for-it logic as the entry point
ENTRYPOINT ["sh", "-c", "\
    while ! nc -z kaddem-db 3306; do \
      echo 'Waiting for the MySQL service...'; \
      sleep 1; \
    done; \
    echo 'MySQL service is up - starting application...'; \
    exec java -jar kaddem.jar \
"]

# Use the Java Runtime Environment (JRE) image to run the Java application
#FROM openjdk:11-jre-slim

# Set the working directory in the container
#WORKDIR /app

# Copy the JAR file from the build directory to the container's working directory
#COPY target/kaddem-0.0.1-SNAPSHOT.jar kaddem.jar

# Inform Docker that the container listens on the specified port at runtime.
#EXPOSE 8089

# Command to run when the container starts
#CMD ["java", "-jar", "kaddem.jar"]


