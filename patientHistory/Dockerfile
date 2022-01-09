
# FROM openjdk:11
# EXPOSE 8082
# ADD build/libs/ui-1.0.0.jar ui.jar
# ENTRYPOINT ["java", "-jar", "ui.jar"]

FROM gradle:jdk11 AS build

COPY build.gradle /app/
COPY src /app/src/

WORKDIR /app/

RUN gradle wrapper

RUN ./gradlew bootJar --no-daemon -DskipTests

FROM adoptopenjdk/openjdk11:latest
# FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8082
COPY --from=build /app/build/libs/patientHistory-1.0.0.jar history.jar


RUN apt-get update && apt-get install -y netcat

COPY wait.sh /wait.sh
RUN chmod +x /wait.sh
ENTRYPOINT ["java", "-jar", "history.jar"]