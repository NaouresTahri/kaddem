FROM openjdk:11
EXPOSE 8080
ADD kaddem-1.0.jar kaddem-1.0.jar
ENTRYPOINT ["java", "-jar", "/kaddem-1.0.jar"]