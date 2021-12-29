
# FROM gradle:jdk11 AS gradleimage
# COPY . /home/gradle/source
# WORKDIR /home/gradle/source
# RUN  gradle clean build -i --stacktrace

# FROM openjdk:11
# EXPOSE 9091
# WORKDIR /app
# COPY --from=gradleimage /home/gradle/source/build/libs/patient-1.0.0.jar /app/

FROM openjdk:11
EXPOSE 9091
ADD build/libs/patient-1.0.0.jar patient.jar
ENTRYPOINT ["java", "-jar", "patient.jar"]