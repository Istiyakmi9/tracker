FROM openjdk:11
EXPOSE 8080
ADD target/bot-tracker-docker.jar bot-tracker-docker.jar
ENTRYPOINT ["java", "jar", "/bot-tracker-docker.jar"]