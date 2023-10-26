# Stage 1: Download the jar from Nexus
FROM alpine:3.13 as downloader

RUN apk --no-cache add wget

WORKDIR /download

RUN wget "http://192.168.33.10:8081/repository/maven-snapshots/tn/esprit/spring/kaddem/0.0.1-SNAPSHOT/kaddem-0.0.1-20231026.201839-5.jar"

# Stage 2: Run the Java application
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the `.jar` file from the downloader stage
COPY --from=downloader /download/kaddem-0.0.1-20231026.201839-5.jar /app

CMD ["java", "-jar", "/app/kaddem-0.0.1-20231026.195733-4.jar"]
