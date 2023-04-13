FROM maven:3.6.3-jdk-11-openj9 AS MAVEN

MAINTAINER BOTTOMHALF

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:11
WORKDIR /app
EXPOSE 8080

COPY --from=MAVEN /build/target/bot-tracker.jar /app/

ENTRYPOINT ["java", "-jar", "bot-tracker.jar"]