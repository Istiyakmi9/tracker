FROM openjdk:11
EXPOSE 8080
ADD /out/artifacts/bot_tracker_jar/bot-tracker.jar bot-tracker.jar
ENTRYPOINT ["java", "jar", "/bot-tracker.jar"]