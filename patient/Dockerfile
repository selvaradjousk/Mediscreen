
# FROM openjdk:11
# EXPOSE 9091
# ADD build/libs/patient-1.0.0.jar patient.jar
# ENTRYPOINT ["java", "-jar", "patient.jar"]


FROM gradle:jdk11 AS build

COPY build.gradle /app/
COPY src /app/src/

WORKDIR /app/


#RUN gradle build --no-daemon -DskipTests

RUN gradle wrapper

#RUN ./gradlew clean bootJar --no-daemon -DskipTests
RUN ./gradlew bootJar --no-daemon -DskipTests

FROM adoptopenjdk/openjdk11:latest
# FROM adoptopenjdk:11-jre-hotspot
EXPOSE 9090
COPY --from=build /app/build/libs/patient-1.0.0.jar patient.jar


RUN apt-get update && apt-get install -y netcat

COPY wait.sh /wait.sh
RUN chmod +x /wait.sh
ENTRYPOINT ["java", "-jar", "patient.jar"]