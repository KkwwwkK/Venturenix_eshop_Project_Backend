FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/project_backend-1.3.0.jar Project_Backend.jar
ENTRYPOINT ["java","-jar","/Project_Backend.jar"]