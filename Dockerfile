FROM openjdk:8
EXPOSE 8089
ADD /target/kaddem-0.0.1.jar kaddem
ENTRYPOINT ["java", "-jar","kaddem"]