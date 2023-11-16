FROM openjdk:11-jdk-slim
ADD target/kaddemProject-1.0.jar ski.jar
ENTRYPOINT ["java", "-jar","kaddem.jar"]
EXPOSE 8086